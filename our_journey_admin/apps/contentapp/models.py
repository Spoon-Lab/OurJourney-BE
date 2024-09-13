from django.db import models

from apps.adminapp.models import Category
from apps.userapp.models import Profile


class Post(models.Model):
    id = models.AutoField(primary_key=True)
    category_id = models.ForeignKey(
        Category, on_delete=models.CASCADE, null=False, blank=False
    )
    profile_id = models.ForeignKey(
        Profile, on_delete=models.CASCADE, null=False, blank=False
    )
    title = models.CharField(max_length=255)
    # 포스트 이미지
    post_img = models.CharField(max_length=200, null=True)
    created_at = models.DateTimeField(auto_now_add=True)
    updated_at = models.DateTimeField(null=True, default=None)
    deleted_at = models.DateTimeField(null=True, default=None)

    class Meta:
        managed = True
        db_table = "post"


class Comment(models.Model):
    id = models.AutoField(primary_key=True)
    profile_id = models.ForeignKey(
        Profile, on_delete=models.CASCADE, null=False, blank=False
    )
    post_id = models.ForeignKey(Post, on_delete=models.CASCADE, null=False, blank=False)
    content = models.CharField(max_length=500)
    created_at = models.DateTimeField(auto_now_add=True)
    updated_at = models.DateTimeField(auto_now=True)
    deleted_at = models.DateTimeField(null=True, default=None)

    class Meta:
        managed = True
        db_table = "comment"


class Tag(models.Model):
    id = models.AutoField(primary_key=True)
    name = models.CharField(max_length=10)
    deleted_at = models.DateTimeField(null=True, default=None)

    class Meta:
        managed = True
        db_table = "tag"


class PostTag(models.Model):
    id = models.AutoField(primary_key=True)
    post_id = models.ForeignKey(Post, on_delete=models.CASCADE, null=False, blank=False)
    tag_id = models.ForeignKey(Tag, on_delete=models.CASCADE, null=False, blank=False)

    class Meta:
        managed = True
        db_table = "post_tag"


class Thread(models.Model):
    id = models.AutoField(primary_key=True)
    profile_id = models.ForeignKey(
        Profile, on_delete=models.CASCADE, null=False, blank=False
    )
    post_id = models.ForeignKey(Post, on_delete=models.CASCADE, null=False, blank=False)
    content = models.CharField(max_length=500)
    # 타래 이미지
    thread_img = models.CharField(max_length=200, null=True)
    created_at = models.DateTimeField(auto_now_add=True)
    updated_at = models.DateTimeField(null=True, default=None)
    deleted_at = models.DateTimeField(null=True, default=None)

    class Meta:
        managed = True
        db_table = "thread"


class ThreadTag(models.Model):
    id = models.AutoField(primary_key=True)
    tag_id = models.ForeignKey(Tag, on_delete=models.CASCADE)
    thread_id = models.ForeignKey(Thread, on_delete=models.CASCADE)

    class Meta:
        managed = True
        db_table = "thread_tag"


class PostLike(models.Model):
    id = models.AutoField(primary_key=True)
    profile_id = models.ForeignKey(
        Profile, on_delete=models.CASCADE, null=False, blank=False
    )
    post_id = models.ForeignKey(Post, on_delete=models.CASCADE, null=False, blank=False)

    class Meta:
        managed = True
        db_table = "post_like"
