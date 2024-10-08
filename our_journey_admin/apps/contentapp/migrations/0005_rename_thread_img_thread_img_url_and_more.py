# Generated by Django 4.2 on 2024-09-24 05:36

import django.db.models.deletion
from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ("userapp", "0003_profile_created_at_profile_updated_at"),
        ("adminapp", "0003_category_created_at"),
        ("contentapp", "0004_tag_created_at_tag_updated_at"),
    ]

    operations = [
        migrations.RenameField(
            model_name="thread",
            old_name="thread_img",
            new_name="img_url",
        ),
        migrations.AlterField(
            model_name="comment",
            name="content_id",
            field=models.ForeignKey(
                db_column="content_id",
                on_delete=django.db.models.deletion.CASCADE,
                to="contentapp.contents",
            ),
        ),
        migrations.AlterField(
            model_name="comment",
            name="profile_id",
            field=models.ForeignKey(
                db_column="profile_id",
                on_delete=django.db.models.deletion.CASCADE,
                to="userapp.profile",
            ),
        ),
        migrations.AlterField(
            model_name="contentlike",
            name="content_id",
            field=models.ForeignKey(
                db_column="content_id",
                on_delete=django.db.models.deletion.CASCADE,
                to="contentapp.contents",
            ),
        ),
        migrations.AlterField(
            model_name="contentlike",
            name="profile_id",
            field=models.ForeignKey(
                db_column="profile_id",
                on_delete=django.db.models.deletion.CASCADE,
                to="userapp.profile",
            ),
        ),
        migrations.AlterField(
            model_name="contents",
            name="category_id",
            field=models.ForeignKey(
                db_column="category_id",
                on_delete=django.db.models.deletion.CASCADE,
                to="adminapp.category",
            ),
        ),
        migrations.AlterField(
            model_name="contents",
            name="profile_id",
            field=models.ForeignKey(
                db_column="profile_id",
                on_delete=django.db.models.deletion.CASCADE,
                to="userapp.profile",
            ),
        ),
        migrations.AlterField(
            model_name="contenttag",
            name="content_id",
            field=models.ForeignKey(
                db_column="content_id",
                on_delete=django.db.models.deletion.CASCADE,
                to="contentapp.contents",
            ),
        ),
        migrations.AlterField(
            model_name="contenttag",
            name="tag_id",
            field=models.ForeignKey(
                db_column="tag_id",
                on_delete=django.db.models.deletion.CASCADE,
                to="contentapp.tag",
            ),
        ),
        migrations.AlterField(
            model_name="thread",
            name="content_id",
            field=models.ForeignKey(
                db_column="content_id",
                on_delete=django.db.models.deletion.CASCADE,
                to="contentapp.contents",
            ),
        ),
        migrations.AlterField(
            model_name="thread",
            name="profile_id",
            field=models.ForeignKey(
                db_column="profile_id",
                on_delete=django.db.models.deletion.CASCADE,
                to="userapp.profile",
            ),
        ),
        migrations.AlterField(
            model_name="threadtag",
            name="tag_id",
            field=models.ForeignKey(
                db_column="tag_id",
                on_delete=django.db.models.deletion.CASCADE,
                to="contentapp.tag",
            ),
        ),
        migrations.AlterField(
            model_name="threadtag",
            name="thread_id",
            field=models.ForeignKey(
                db_column="thread_id",
                on_delete=django.db.models.deletion.CASCADE,
                to="contentapp.thread",
            ),
        ),
    ]
