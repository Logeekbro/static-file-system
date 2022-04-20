var v = new Vue({
    el: "#main",
    data() {
        return {
            "openUpload": false,
            "fileList": [],
            "tableData": [],
            "search": "",
            "currentDirPath": "",
            "dirInfo": {},
            "fileSep": "/",
            "pathList": [],
            "uploadData": {},
            "selectedRows": [],
            "showCreateDirDialog": false,
            "newDirInputValue": "",
            "jumpToNewDir": false,
            "copiedRows": [],
            "emptyText": "",
            "pasteMode": "copy"

        }
    },
    methods: {
        getFileList(path=null){
            this.tableData = [];
            this.emptyText = "加载中...";
            if(path == null){
                path = this.currentDirPath;
            }
            else{
                this.currentDirPath = path;
            }
            this.setCurrentDir();
            axios({
                method: "get",
                url : "/file/getFileList",
                params: {
                    path: path
                }
            }).then(res => {
                this.emptyText = "暂无数据";
                var data = res.data;
                if(data.success){
                    this.dirInfo.name = data.data.dirName;
                    this.dirInfo.path = data.data.dirPath;
                    this.uploadData = {
                        "path": this.dirInfo.path + this.fileSep
                    };
                    this.dirInfo.fileCount = data.data.fileCount
                    this.tableData = data.data.dirs.concat(data.data.files);
                    this.getRoutePath();
                }
                else{
                    this.$message({
                        message: data.msg,
                        type: 'error',
                        duration: 2500
                    });
                }

            }).catch(error => {
                this.emptyText = "加载失败!";
                this.$message({
                    message: '获取文件信息失败:' + error.message,
                    type: 'error',
                    duration: 2500
                });
            });

        },
        getCurrentDir(){
            return axios({
                method: "get",
                url: "/client/currentDir",
            }).then(res => {
                this.currentDirPath = res.data.data == null ? "/" : res.data.data;
                this.getFileList();
            });
        },
        setCurrentDir(){
            var params = new URLSearchParams();
            params.append("currentDir", this.currentDirPath)
            axios({
                method: "put",
                url: "/client/currentDir",
                data: params
            });
        },
        clearFileList(){
            this.fileList = [];
        },
        on_success(rep, file){
            if(rep.success){
                this.$message({
                    message: '上传成功！',
                    type: 'success',
                    duration: 1200
                });
            }
            else{
                this.$message({
                    message: rep.msg + "->" + file.name,
                    type: 'error',
                    duration: 2500
                });
            }
            this.clearFileList();
            this.getFileList();

        },
        on_error(err,file){
            console.log(err)
            setTimeout(this.clearFileList, 1000);
            this.$message({
                message: file.name + '上传失败！',
                type: 'error',
                duration: 1200
            });
        },
        submitUpload() {
            this.$refs.upload.submit();
        },
        handleRemove(file, fileList) {
            console.log(file, fileList);
        },
        handlePreview(file) {
            console.log(file);
        },
        handleSelectionChange(selection){
            this.selectedRows = selection;
        },
        handleEdit(index, rowData){
            this.$message({
                message: '复制成功！',
                type: 'success',
                duration: 1000
            });
        },
        on_upload_close(done){
            if(this.fileList.length === 0){
                done();
                this.getFileList();
            }
            else{
                this.$confirm('还有文件上传未完成，现在关闭可能会导致无法预料的错误，确认关闭？')
                    .then(_ => {
                        done();
                        this.getFileList();
                    })
                    .catch(_ => {});
            }
        },
        DeleteFile(index, rowData, showTip=true){
            var params = new URLSearchParams();
            params.append("path", rowData.url)
            axios({
                method: "post",
                url: "/file/delete",
                data: params
            }).then(res => {
                var data = res.data;
                if(data.success){
                    if(showTip){
                        this.$message({
                            message: '删除成功！',
                            type: 'success',
                            duration: 1000
                        });
                        this.getFileList();
                    }
                }
                else{
                    if(showTip){
                        this.$message({
                            message: '删除失败: ' + data.msg,
                            type: 'error',
                            duration: 1000
                        });
                    }
                }
            }).catch(error => {
                this.$message({
                    message: '服务器错误: ' + error.message,
                    type: 'error',
                    duration: 2500
                });
            });
        },
        DeleteDir(index, rowData, showTip=true){
            var params = new URLSearchParams();
            params.append("path", rowData.dirPath);
            axios({
                method: "post",
                url: "/file/dir/delete",
                data: params
            }).then(res => {
                var data = res.data;
                if(data.success){
                    if(showTip){
                        this.$message({
                            message: '删除成功！',
                            type: 'success',
                            duration: 1000
                        });
                        this.getFileList();
                    }

                }
                else{
                    if(showTip){
                        this.$message({
                            message: '删除失败: ' + data.msg,
                            type: 'error',
                            duration: 1000
                        });
                    }
                }
            }).catch(error => {
                this.$message({
                    message: '服务器错误: ' + error.message,
                    type: 'error',
                    duration: 2500
                });
            });
        },
        createDir(){
            var params = new URLSearchParams();
            var path = this.dirInfo.path + this.newDirInputValue;
            params.append("path", path);
            axios({
                method: "post",
                url: "/dir/create",
                data: params
            }).then(res => {
                    var data = res.data;
                    if(data.success){
                        this.$message({
                            message: '创建成功！',
                            type: 'success',
                            duration: 1000
                        });
                        if(this.jumpToNewDir){
                            this.currentDirPath = path;
                        }
                        this.newDirInputValue = "";
                        this.getFileList();
                    }
                    else{
                        this.$message({
                            message: '创建失败: ' + data.msg,
                            type: 'error',
                            duration: 1000
                        });
                    }
            });
            this.showCreateDirDialog = false;
        },
        multiDelete(){
            var selected = this.selectedRows;
            var len = selected.length;
            var failNum = 0;
            for(var i = 0; i < len;i++){
                if(selected[i].type === "file"){
                    this.DeleteFile(i, selected[i]);
                    failNum += !this.runningResult ? 1 : 0;
                }
                else if(selected[i].type === "dir"){
                    this.DeleteDir(i, selected[i])
                    failNum += !this.runningResult ? 1 : 0;
                }
            }

        },
        getFormatDate(stamp) {
            var date = new Date(stamp);
            var seperator1 = "-";
            var seperator2 = ":";
            var year = date.getFullYear();
            var month = date.getMonth() + 1;
            var strDate = date.getDate();
            var hour = date.getHours();
            var min = date.getMinutes();
            var sec = date.getSeconds();
            // if (month >= 1 && month <= 9) {
            //     month = "0" + month;
            // }
            // if (strDate >= 0 && strDate <= 9) {
            //     strDate = "0" + strDate;
            // }
            if(min >= 0 && min <= 9){
                min = "0" + min
            }
            if(sec >= 0 && sec <= 9){
                sec = "0" + sec
            }
            var currentdate = year + seperator1 + month + seperator1 + strDate + "  " +
                hour + seperator2 + min + seperator2 + sec;
            return currentdate;
        },
        getRoutePath(){
            var dirInfo = this.dirInfo;
            var fullpath = dirInfo.path;
            var paths = fullpath.split(this.fileSep);
            var len = paths.length;
            var addPath = "";
            var pl = []
            for(var i = 0;i < len;i++){
                if(i !== 0){
                    addPath += this.fileSep + paths[i];
                }
                else{
                    addPath += paths[i];
                }
                po = {
                    "name": paths[i],
                    "path": addPath
                };
                pl.push(po);
            }
            this.pathList = pl;
        },
        getFileSep(){
            axios({
                method: "get",
                url: "/env/getFileSep"
            }).then(res => {
                this.fileSep = res.data;
            });
        },
        moveAndCopyFile(source, target){
            var url = "/file/copy";
            var params = new URLSearchParams();
            params.append("source", source);
            params.append("target", target);
            if(this.copyMode === "cut"){
                url = "/file/move";
            }
            axios({
                method: "post",
                url: url,
                data: params
            }).then(res => {
                var data = res.data;
                if(data.success){
                    this.getFileList();
                }
                else{
                    this.$message({
                        message: '操作失败: ' + data.msg,
                        type: 'error',
                        duration: 2500
                    });
                }
            });
        },
        copyToList(){
            this.copiedRows = [];
            var list = this.selectedRows;
            for(var i = 0;i < list.length;i++){
                if(list[i].type === "file"){
                    list[i].dir = this.dirInfo.path;
                }
                this.copiedRows.push(list[i]);
            }

            // 清除选中
            this.$refs.multipleTable.clearSelection();
            this.copyMode = "copy";
            this.$message({
                message: '已复制',
                type: 'success',
                duration: 1000
            });
        },
        cutToList(){
            this.copiedRows = [];
            var list = this.selectedRows;
            for(var i = 0;i < list.length;i++){
                if(list[i].type === "file"){
                    list[i].dir = this.dirInfo.path;
                }
                this.copiedRows.push(list[i]);
            }
            // 清除选中
            this.$refs.multipleTable.clearSelection();
            this.copyMode = "cut";
            this.$message({
                message: '已剪切',
                type: 'success',
                duration: 1000
            });
        },
        pasteData(){
            console.log("start " + this.copyMode)
            var list = this.copiedRows;
            for(var i = 0; i < list.length;i++){
                if(list[i].type === "dir"){
                    var source = list[i].dirPath;
                    var target = this.dirInfo.path + list[i].dirName;
                    this.moveAndCopyFile(source, target);
                }
                else if(list[i].type === "file"){
                    var source = list[i].dir + list[i].fileName;
                    var target = this.dirInfo.path + list[i].fileName;
                    this.moveAndCopyFile(source, target);
                }
                console.log(list[i]);
            }
            this.copiedRows = [];
        },

    },

    mounted: function (){
        this.getCurrentDir();
        this.getFileSep();

    }
})