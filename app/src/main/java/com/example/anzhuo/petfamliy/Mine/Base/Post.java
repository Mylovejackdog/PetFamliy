package com.example.anzhuo.petfamliy.Mine.Base;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.datatype.BmobRelation;

/**
 * 发布的帖子
 * Created by anzhuo on 2016/11/9.
 */

public class Post extends BmobObject {
    private String title;//帖子标题
    private String content;// 帖子内容
    private User author;//帖子的发布者，这里体现的是一对一的关系，该帖子属于某个用户
    private BmobFile image;//帖子图片
    private BmobRelation likes;//多对多关系：用于存储喜欢该帖子的所有用户

}
