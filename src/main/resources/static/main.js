var v = new Vue({
    el: "#main",
    data() {
        return {
            "fileList": [],
            "tableData": [],
            "search": "",
            "currentDirPath": "",
            "dirInfo": {},
            "fileSep": "/",
            "pathList": [],
            "uploadData": {}
        }
    },
    methods: {
        getFileList(){
            axios({
                method: "get",
                url : "/file/getFileList",
                params: {
                    path:this.currentDirPath
                }
            }).then(res => {
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
                this.$message({
                    message: '获取文件信息失败:' + error.message,
                    type: 'error',
                    duration: 2500
                });
            });
        },
        clearFileList(){
            this.fileList = [];
        },
        on_success(){
            setTimeout(this.clearFileList, 1200);
            this.getFileList();
        },
        on_error(err){
            console.log(err)
            this.$message({
                message: '上传失败！',
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
        handleSelectionChange(){

        },
        handleEdit(index, rowData){
            this.$message({
                message: '复制成功！',
                type: 'success',
                duration: 1000
            });
        },
        DeleteFile(index, rowData){
            var params = new URLSearchParams();
            params.append("path", rowData.url)
            axios({
                method: "post",
                url: "/file/deleteFile",
                data: params
            }).then(res => {
                var data = res.data;
                if(data.success){
                    this.$message({
                        message: '删除成功！',
                        type: 'success',
                        duration: 1000
                    });
                    this.getFileList();
                }
                else{
                    this.$message({
                        message: '删除失败: ' + data.msg,
                        type: 'error',
                        duration: 1000
                    });
                }
            }).catch(error => {
                this.$message({
                    message: '服务器错误: ' + error.message,
                    type: 'error',
                    duration: 2500
                });
            });
        },
        DeleteDir(index, rowData){
            var params = new URLSearchParams();
            params.append("path", rowData.dirPath);
            axios({
                method: "post",
                url: "/file/deleteDir",
                data: params
            }).then(res => {
                var data = res.data;
                if(data.success){
                    this.$message({
                        message: '删除成功！',
                        type: 'success',
                        duration: 1000
                    });
                    this.getFileList();
                }
                else{
                    this.$message({
                        message: '删除失败: ' + data.msg,
                        type: 'error',
                        duration: 1000
                    });
                }
            }).catch(error => {
                this.$message({
                    message: '服务器错误: ' + error.message,
                    type: 'error',
                    duration: 2500
                });
            });
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
                    "url": "/?path=" + encodeURIComponent(addPath)
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
        }
    },

    mounted: function (){
        this.currentDirPath = getQueryVariable("path")
        this.getFileSep();
        this.getFileList();
    }
})