from django.db import models


class Profile(models.Model):
    id = models.AutoField(primary_key=True)
    nick_name = models.CharField(max_length=100, null=True)
    # 프로필 이미지 url
    img_url = models.CharField(max_length=200, null=True)
    # auth db에서의 user pk값
    user_id = models.BigIntegerField(null=False, blank=False)
    self_introduction = models.CharField(max_length=100, null=True)
    created_at = models.DateTimeField(auto_now_add=True)
    updated_at = models.DateTimeField(null=True, default=None)
    deleted_at = models.DateTimeField(null=True, default=None)

    class Meta:
        managed = True
        db_table = "profile"


class Follow(models.Model):
    id = models.AutoField(primary_key=True)
    # 팔로잉
    following_user_id = models.ForeignKey(
        Profile,
        on_delete=models.CASCADE,
        related_name="following",
        db_column="following_user_id",
    )
    # 팔로워
    follower_user_id = models.ForeignKey(
        Profile,
        on_delete=models.CASCADE,
        related_name="follower",
        db_column="follower_user_id",
    )

    class Meta:
        managed = True
        db_table = "follow"


class Attendee(models.Model):
    id = models.AutoField(primary_key=True)
    profile_id = models.ForeignKey(
        Profile,
        on_delete=models.CASCADE,
        null=False,
        blank=False,
        db_column="profile_id",
    )
    content_id = models.ForeignKey(
        "contentapp.Contents",
        on_delete=models.CASCADE,
        null=False,
        blank=False,
        db_column="content_id",
    )

    class Meta:
        managed = True
        db_table = "attendee"
