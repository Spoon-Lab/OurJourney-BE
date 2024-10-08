# Generated by Django 4.2 on 2024-09-20 06:22

import django.db.models.deletion
from django.db import migrations, models


class Migration(migrations.Migration):

    initial = True

    dependencies = [
        ("adminapp", "0001_initial"),
        ("userapp", "0001_initial"),
        ("contentapp", "0001_initial"),
    ]

    operations = [
        migrations.AddField(
            model_name="thread",
            name="profile_id",
            field=models.ForeignKey(
                on_delete=django.db.models.deletion.CASCADE, to="userapp.profile"
            ),
        ),
        migrations.AddField(
            model_name="contenttag",
            name="content_id",
            field=models.ForeignKey(
                on_delete=django.db.models.deletion.CASCADE, to="contentapp.contents"
            ),
        ),
        migrations.AddField(
            model_name="contenttag",
            name="tag_id",
            field=models.ForeignKey(
                on_delete=django.db.models.deletion.CASCADE, to="contentapp.tag"
            ),
        ),
        migrations.AddField(
            model_name="contents",
            name="category_id",
            field=models.ForeignKey(
                on_delete=django.db.models.deletion.CASCADE, to="adminapp.category"
            ),
        ),
        migrations.AddField(
            model_name="contents",
            name="profile_id",
            field=models.ForeignKey(
                on_delete=django.db.models.deletion.CASCADE, to="userapp.profile"
            ),
        ),
        migrations.AddField(
            model_name="contentlike",
            name="content_id",
            field=models.ForeignKey(
                on_delete=django.db.models.deletion.CASCADE, to="contentapp.contents"
            ),
        ),
        migrations.AddField(
            model_name="contentlike",
            name="profile_id",
            field=models.ForeignKey(
                on_delete=django.db.models.deletion.CASCADE, to="userapp.profile"
            ),
        ),
        migrations.AddField(
            model_name="comment",
            name="content_id",
            field=models.ForeignKey(
                on_delete=django.db.models.deletion.CASCADE, to="contentapp.contents"
            ),
        ),
        migrations.AddField(
            model_name="comment",
            name="profile_id",
            field=models.ForeignKey(
                on_delete=django.db.models.deletion.CASCADE, to="userapp.profile"
            ),
        ),
    ]
