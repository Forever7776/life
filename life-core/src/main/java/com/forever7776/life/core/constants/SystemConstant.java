package com.forever7776.life.core.constants;


import static util.ConfigTool.getProp;

/**
 * @author kz
 * @date 2017年12月1日14:32:07
 * @desc 七牛参数
 */
public class SystemConstant {


    public static final String QINIU_ACCESS = getProp("qiniu.access");
    public static final String QINIU_SECRET = getProp("qiniu.secret");
    public static final String QINIU_BUCKET = getProp("qiniu.bucket");
    /**
     * 接受上传图片的类型
     */
    public static final String QINIU_SUFFIXIMAGE = getProp("qiniu.suffixImage");
    /**
     * 重命名图片名称连接符号
     */
    public static final String QINIU_FILELINK = getProp("qiniu.filelink");
}
