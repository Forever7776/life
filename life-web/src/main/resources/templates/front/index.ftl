<#include "../inc/inc.ftl"/>
<@html webupload_=true fontawesome=true>
<div style="padding: 100px 100px 10px;">
    <div class="form-group">
        <label class="col-xs-2 col-xs-2 control-label"><i class="fa fa-picture-o" aria-hidden="true"></i>图片：</label>
        <div class="col-xs-10">

            <div id="goodsImages">
                <div class="goodsImageList filled">
                    <ul class="imagelist">
                        <#if (goods.images)??>
                            <#list goods.images as previewImg>
                                <li id="WU_FILE_${previewImg.file_key!''}"
                                    class="preview-image-li <#if (goods.cover_image_key!'')==previewImg.file_key>cover-select<#else >no-cover-select</#if>">
                                    <p class="title">${previewImg.file_key!''}</p>
                                    <p class="imgWrap"><img src="${imgurl+previewImg.file_key}"></p>
                                    <p class="progress"><span></span></p>
                                    <div class="file-panel" style="height:30px;">
                                        <i class="fa fa-heart" <#if (goods.cover_image_key!'')!=previewImg.file_key>
                                           onclick="setCoverKey(this,'${previewImg.file_key!0}')" </#if>
                                           title="设为封面图片"></i>
                                        <i class="fa fa-trash-o"
                                           onclick="deletePreviewImage(this,'${previewImg.file_key!0}')"
                                           title="删除图片"></i>
                                        <!--
                                        <span class="rotateRight">向右旋转</span>
                                        <span class="rotateLeft">向左旋转</span>
                                        -->
                                    </div>
                                </li>
                            </#list>
                        </#if>
                    </ul>
                </div>
            </div>

            <div id="uploader" class="wu-example">
                <div class="queueList">
                    <div id="dndArea" class="placeholder">
                        <div id="filePicker"></div>
                        <p>或将照片拖到这里，单次最多可选300张(720px*480px以下)</p>
                    </div>
                </div>
                <div class="statusBar" style="display:none;">
                    <div class="progress">
                        <span class="text">0%</span>
                        <span class="percentage"></span>
                    </div>
                    <div class="info"></div>
                    <div class="btns">
                        <div id="filePicker2"></div>
                        <div class="uploadBtn">开始上传</div>
                    </div>
                </div>
            </div>

        </div>
    </div>
    <br/><br/>
    <button class="btn btn-default" type="submit">确定</button>
</div>

<script>
    function uploadSuccess(f, d) {
        console.log("f:" + f)
        console.log("d:" + d)
        console.log("key:" + d.key)
        var goods_code = $("#goods_code").val();
        var data = {"goods_code": goods_code, "fileId": d.id, "file_key": d.key, "url": d.url};
    }

</script>
</@html>