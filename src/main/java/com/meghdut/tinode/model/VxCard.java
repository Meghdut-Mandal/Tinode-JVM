package com.meghdut.tinode.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import java.awt.image.BufferedImage;

import co.tinode.tinodesdk.model.Mergeable;
import co.tinode.tinodesdk.model.VCard;

/**
 * VxCard - contact descriptor.
 * Adds avatar conversion from bits to Android BufferedImage and back.
 */
public class VxCard extends VCard {
    @JsonIgnore
    public AvatarPhoto avatar;

    public VxCard() {
    }

    public VxCard(String fullName, byte[] avatar, String avatarImageType) {
        super(fullName, avatar, avatarImageType);
        constructBufferedImage();
    }

    public VxCard(String fullName, BufferedImage bmp) {
        fn = fullName;
        if (bmp != null) {
            avatar = new AvatarPhoto(bmp);
            photo = new Photo(avatar.data, avatar.type);
        }
    }

    @Override
    public VxCard copy() {
        VxCard dst = copy(new VxCard(), this);
        dst.avatar = avatar;
        return dst;
    }

    @JsonIgnore
    public BufferedImage getBufferedImage() {
        if (avatar == null) {
            constructBufferedImage();
        }
        return (avatar != null) ? avatar.getBufferedImage() : null;
    }

    @JsonIgnore
    public void setBufferedImage(BufferedImage bmp) {
        avatar = new AvatarPhoto(bmp);
        photo = new Photo(avatar.data, avatar.type);
    }

    @JsonIgnore
    public void setAvatar(AvatarPhoto bmp) {
        avatar = bmp;
        photo = new Photo(avatar.data, avatar.type);
    }

    public void constructBufferedImage() {
        if (photo != null) {
            avatar = new AvatarPhoto(photo.data);
        }
    }

    @JsonIgnore
    @Override
    public int merge(Mergeable another) {
        if (!(another instanceof VxCard)) {
            return 0;
        }
        int changed = super.merge(another);
        VxCard avc = (VxCard) another;
        if (avc.avatar != null) {
            avatar = avc.avatar;
            changed++;
        }
        return changed;
    }
}
