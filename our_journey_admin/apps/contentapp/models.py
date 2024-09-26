from django.db import models

from apps.adminapp.models import Category
from apps.userapp.models import Profile


class Contents(models.Model):
    id = models.AutoField(primary_key=True)
    category_id = models.ForeignKey(
        Category,
        on_delete=models.CASCADE,
        null=False,
        blank=False,
        db_column="category_id",
    )
    profile_id = models.ForeignKey(
        Profile,
        on_delete=models.CASCADE,
        null=False,
        blank=False,
        db_column="profile_id",
    )
    title = models.CharField(max_length=255)
    # content 이미지
    img_url = models.CharField(max_length=1000, null=True)
    created_at = models.DateTimeField(auto_now_add=True)
    updated_at = models.DateTimeField(null=True, default=None)
    deleted_at = models.DateTimeField(null=True, default=None)

    class Meta:
        managed = True
        db_table = "contents"


class Comment(models.Model):
    id = models.AutoField(primary_key=True)
    profile_id = models.ForeignKey(
        Profile,
        on_delete=models.CASCADE,
        null=False,
        blank=False,
        db_column="profile_id",
    )
    content_id = models.ForeignKey(
        Contents,
        on_delete=models.CASCADE,
        null=False,
        blank=False,
        db_column="content_id",
    )
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
    created_at = models.DateTimeField(auto_now_add=True)
    updated_at = models.DateTimeField(null=True, default=None)
    deleted_at = models.DateTimeField(null=True, default=None)

    class Meta:
        managed = True
        db_table = "tag"


class ContentTag(models.Model):
    id = models.AutoField(primary_key=True)
    content_id = models.ForeignKey(
        Contents,
        on_delete=models.CASCADE,
        null=False,
        blank=False,
        db_column="content_id",
    )
    tag_id = models.ForeignKey(
        Tag, on_delete=models.CASCADE, null=False, blank=False, db_column="tag_id"
    )

    class Meta:
        managed = True
        db_table = "content_tag"


class Thread(models.Model):
    id = models.AutoField(primary_key=True)
    profile_id = models.ForeignKey(
        Profile,
        on_delete=models.CASCADE,
        null=False,
        blank=False,
        db_column="profile_id",
    )
    content_id = models.ForeignKey(
        Contents,
        on_delete=models.CASCADE,
        null=False,
        blank=False,
        db_column="content_id",
    )
    content = models.CharField(max_length=500)
    # 타래 이미지
    img_url = models.CharField(max_length=1000, null=True)
    created_at = models.DateTimeField(auto_now_add=True)
    updated_at = models.DateTimeField(null=True, default=None)
    deleted_at = models.DateTimeField(null=True, default=None)

    class Meta:
        managed = True
        db_table = "thread"


class ThreadTag(models.Model):
    id = models.AutoField(primary_key=True)
    tag_id = models.ForeignKey(Tag, on_delete=models.CASCADE, db_column="tag_id")
    thread_id = models.ForeignKey(
        Thread, on_delete=models.CASCADE, db_column="thread_id"
    )

    class Meta:
        managed = True
        db_table = "thread_tag"


class ContentLike(models.Model):
    id = models.AutoField(primary_key=True)
    profile_id = models.ForeignKey(
        Profile,
        on_delete=models.CASCADE,
        null=False,
        blank=False,
        db_column="profile_id",
    )
    content_id = models.ForeignKey(
        Contents,
        on_delete=models.CASCADE,
        null=False,
        blank=False,
        db_column="content_id",
    )

    class Meta:
        managed = True
        db_table = "content_like"
