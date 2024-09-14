from django.db import models


class AuthUser(models.Model):
    id = models.AutoField(primary_key=True)
    email = models.EmailField(unique=True)

    class Meta:
        managed = False  # Django가 이 모델을 관리하지 않도록 설정
        db_table = "authapp_user"


class Profile(models.Model):
    id = models.AutoField(primary_key=True)
    nick_name = models.CharField(max_length=100, null=True)
    # 프로필 이미지 url
    profile_img = models.CharField(max_length=200, null=True)
    # auth db에서의 user pk값
    user_id = models.ForeignKey(
        AuthUser, on_delete=models.CASCADE, null=False, blank=False
    )
    self_introduction = models.TextField(blank=True)
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
    )
    # 팔로워
    follower_user_id = models.ForeignKey(
        Profile,
        on_delete=models.CASCADE,
        related_name="follower",
    )

    class Meta:
        managed = True
        db_table = "follow"


class Attendee(models.Model):
    id = models.AutoField(primary_key=True)
    profile_id = models.ForeignKey(
        Profile, on_delete=models.CASCADE, null=False, blank=False
    )
    content_id = models.ForeignKey(
        "contentapp.Contents", on_delete=models.CASCADE, null=False, blank=False
    )

    class Meta:
        managed = True
        db_table = "attendee"
