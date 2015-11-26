/**
 * Created with JetBrains WebStorm.
 * User: Administrator
 * Date: 14-6-23
 * Time: 上午11:34
 * upload file for imgaes.
 */
(function (window) {
    "use strict";

    var upload;
    var inner;
    var Upload = function () {

    };

    //noinspection FunctionWithInconsistentReturnsJS
    Upload.fn = Upload.prototype = {

        /**
         * success: function(data, img){input[type=file] == this}
         * @param options
         * @returns {boolean}
         */

        file: function (options) {
            var dom = inner.byId(options.id);
            var children, img, bar, file;

            //dom whether is null
            if (dom === null) {
                return false;
            }

            //init variables
            children = inner.children(dom);
            img = inner.children(children[0])[0];
            bar = children[1];
            file = children[2];
            file.style.display = 'none';

            inner.bind('click', dom, function(){
                file.click();
            });

            inner.bind('change', file, function () {
                uploadImg.call(this, {
                    bar: bar,
                    form: file.form,
                    img: img,
                    src: options.src,
                    speed: options.speed,
                    fail: function () {
                        if (options.fail) {
                            options.fail.call(this, arguments);
                        }
                    },
                    success: function (data) {
                        if (options.success) {
                            options.success.call(this, data, img);
                        }
                    }
                });
            });
        }
    };

    //noinspection FunctionWithInconsistentReturnsJS
    inner = {

        /**
         * Gets the tag DOM object
         * @param id {String}
         * @returns {HTMLElement}
         */
        byId: function (id) {
            var dom = document.getElementById(id);

            if (dom === null) {
                return dom;
            }

            if (dom.id !== id && document.all[id].length) {

                for (var i = 0, len = document.all[id].length; i < len; i++) {
                    dom = document.all[id][i];

                    if (dom.id === id) {
                        return dom;
                    }
                }
            } else if (dom.id === id) {
                return dom;
            }
        },

        /**
         * Direct access to the DOM object node elements
         * @param dom {HTMLObjectElement}
         * @returns {Array} or {null}
         */
        children: function (dom) {
            var ary = null;
            var child = null;

            if (dom.hasChildNodes()) {
                ary = [];

                for (var i = 0, len = dom.childNodes.length; i < len; i++) {
                    child = dom.childNodes[i];

                    if (Number(child.nodeType) === 1) {
                        ary.push(child);
                    }
                }
                return ary;
            } else {
                return ary;
            }
        },

        /**
         * The DOM object is registered binding events
         * @param type {String}
         * @param dom {HTMLObjectElement}
         * @param fn {Function}
         * @returns {Function}
         */
        bind: function (type, dom, fn) {
            fn.guid = !fn.guid ? function () {
                fn.call(dom, arguments);
            } : fn.guid;

            if (window.addEventListener) {
                dom.addEventListener(type, fn.guid, false);
            } else {
                dom.attachEvent('on' + type, fn.guid);
            }
            return fn;
        },

        /**
         * The DOM object delete unbinding events
         * @param type {String}
         * @param dom {HTMLObjectElement}
         * @param fn {Function}
         * @returns {Function}
         */
        unbind: function (type, dom, fn) {
            if (window.addEventListener) {
                dom.removeEventListener(type, fn.guid, false);
            } else {
                dom.detachEvent('on' + type, fn.guid);
            }

            fn.guid = null;
            return fn.guid;
        },

        /**
         * Judge whether the URL image file
         * @param url {string}
         * @returns {boolean}
         */
        isImg: function (url) {
            var rep = /(.jpg|.png|.gif|.jpeg|.JPG|.PNG|.GIF|.JPEG)$/;

            return rep.test(url);
        },

        /**
         * Gets the corresponding frame object
         * @param name
         * @returns {HTMLObjectElement}
         */
        frame: function (name) {
            //noinspection JSUnusedAssignment
            var fms = null;
            var frames = document.getElementsByName(name);

            for (var i = 0, len = frames.length; i < len; i++) {

                if (frames[i].name === name && frames[i].id === name) {
                    frames[i].parentNode.removeChild(frames[i]);
                    frames[i] = null;
                }
            }

            fms = document.createElement('iframe');
            document.body.appendChild(fms);
            fms.contentWindow.name = name;
            fms.setAttribute('name', name);
            fms.setAttribute('id', name);
            fms.setAttribute('frameborder', '0');
            fms.style.cssText = 'position:absolute; top:-9999px; left:-9999px; height:0; overflow:hidden;';

            return fms;
        }
    };

    function fileReader(img) {
        var reader = new FileReader();

        reader.readAsDataURL(this.files[0]);
        inner.bind('load', reader, function () {
            //noinspection JSValidateTypes
            img.setAttribute('src', this.result);
        });
    }

    function formUpload(form) {
        var frame = inner.frame('uploadImgFrame');
        form.setAttribute('target', frame.name);
        form.setAttribute('enctype', 'multipart/form-data');
        form.submit();
        return frame;
    }

    //noinspection FunctionWithInconsistentReturnsJS
    function uploadImg(options) {
        //noinspection JSUnusedAssignment
        var self = this;
        var frame = null;
        var timer = null;
        var loop = 0;
        show_progress()
        if (!inner.isImg(self.value)) {
            options.fail.call(self, arguments);
            return false;
        }

        frame = formUpload(options.form);

        inner.bind('load', frame, function () {
            //noinspection JSUnresolvedVariable
			var frame = this;
            var doc = window.frames[this.name].document;
            var data = doc.body.innerHTML;

            clearInterval(timer);
            //options.bar.style.display = 'block';

            //options.bar.style.width = '0%';

            timer = setInterval(function(){
                if(loop < 100){
                    var p_val =  (loop+=10) + "%";
                    $("#progress_00000 .progress-val").eq(0).html(p_val);
                    $("#progress_00000 .progress-in").eq(0).css("width",p_val);
                } else {
                    clearInterval(timer);
                    options.bar.style.width = '100%';
                    $("#progress_00000 .progress-in").eq(0).css("width","100%");
                    $("#progress_00000").hide(0);

                    //If the browser supports FileReader
                    if (window.FileReader) {
                        fileReader.call(self, options.img);
                    }
                    options.success.call(self, data);
					frame.parentNode.removeChild(frame);
                }
            }, 20);
        });
    }

    upload = new Upload();
    Upload.prototype = upload;
    window.upload = upload;
})(window);
