package org.jcodec.scale;

import org.jcodec.common.ArrayUtil;
import org.jcodec.common.model.ColorSpace;
import org.jcodec.common.model.Picture;
import org.junit.Assert;
import org.junit.Test;

import java.lang.System;

public class TestRgbToYuv420 {

    @Test
    public void testRGBToYuv420() {

        int[] rgb = { 3, 192, 105, 19, 153, 61, 125, 114, 16, 175, 213, 20, 186, 252, 183, 122, 99, 236, 255, 50, 72,
                45, 127, 129, 54, 114, 84, 96, 52, 65, 73, 226, 196, 131, 90, 195, 85, 197, 130, 190, 147, 86, 104, 177,
                233, 72, 205, 82, 18, 41, 88, 123, 140, 54, 5, 132, 129, 47, 137, 69, 34, 208, 223, 167, 89, 36, 176,
                124, 153, 144, 91, 153, 170, 114, 63, 185, 145, 167, 92, 179, 36, 52, 95, 30, 68, 97, 33, 29, 189, 136,
                211, 118, 55, 149, 102, 161, 47, 249, 48, 167, 157, 131, 117, 244, 186, 241, 80, 72, 109, 178, 104, 20,
                233, 121, 224, 93, 142, 171, 99, 61, 72, 192, 108, 149, 165, 193, 146, 138, 63, 43, 97, 133, 97, 35, 25,
                73, 149, 229, 198, 190, 84, 245, 92, 142, 52, 42, 189, 191, 178, 58, 73, 156, 71, 214, 6, 28, 136, 172,
                120, 34, 199, 234, 15, 82, 71, 240, 89, 107, 251, 4, 86, 49, 170, 165, 204, 248, 166, 17, 29, 210, 95,
                80, 196, 42, 201, 170, 188, 111, 243, 33, 231, 237, 50, 223, 16, 176, 233, 2, 61, 147, 94, 111, 207, 43,
                5, 133, 109, 70, 70, 167, 199, 50, 37, 145, 14, 190, 34, 94, 74, 54, 58, 231, 10, 54, 83, 227, 74, 74,
                56, 228, 48, 179, 226, 179, 247, 152, 23, 20, 86, 39, 75, 94, 210, 212, 43, 117, 174, 7, 147, 250, 130,
                243, 119, 142, 66, 74, 207, 237, 249, 190, 209, 174, 173, 0, 155, 11, 156, 7, 216, 247, 122, 232, 52,
                65, 129, 139, 40, 10, 205, 184, 40, 79, 4, 220, 90, 123, 69, 252, 209, 75, 52, 198, 151, 96, 45, 199,
                20, 230, 232, 24 };
        int[] y = { 124, 104, 107, 170, 209, 120, 114, 104, 96, 73, 168, 114, 150, 147, 155, 146, 50, 124, 96, 104, 151,
                107, 139, 114, 123, 153, 133, 80, 86, 132, 135, 122, 158, 151, 187, 125, 144, 150, 134, 116, 142, 156,
                129, 89, 61, 132, 171, 139, 69, 161, 120, 77, 149, 148, 68, 133, 91, 130, 210, 56, 100, 145, 144, 164,
                143, 179, 115, 153, 95, 86, 96, 79, 79, 82, 54, 119, 150, 194, 159, 68, 103, 104, 79, 170, 125, 163,
                196, 148, 77, 151, 169, 111, 51, 91, 137, 107, 113, 153, 130, 195 };
        int[] u = { 118, 108, 83, 49, 108, 185, 107, 141, 124, 127, 138, 168, 115, 95, 163, 94, 152, 93, 146, 112, 160,
                93, 133, 147, 97, 132, 78, 106, 104, 129, 87, 147, 70, 115, 121, 101, 106, 111, 130, 101, 109, 143, 94,
                152, 114, 174, 80, 127, 191, 74, 103, 107, 111, 168, 133, 113, 127, 144, 99, 209, 177, 138, 174, 160,
                63, 35, 118, 71, 137, 170, 100, 186, 128, 204, 147, 105, 75, 114, 57, 117, 182, 135, 164, 160, 98, 161,
                128, 52, 170, 173, 66, 142, 209, 124, 103, 200, 98, 97, 73, 37 };
         
        int[] v = { 51, 76, 140, 125, 104, 128, 216, 92, 104, 146, 63, 139, 84, 151, 92, 78, 115, 127, 73, 93, 51, 166,
                149, 147, 156, 144, 100, 114, 120, 62, 173, 144, 54, 134, 77, 199, 103, 43, 182, 162, 81, 119, 137, 102,
                156, 89, 139, 191, 122, 142, 98, 217, 116, 53, 99, 193, 230, 75, 115, 110, 126, 61, 152, 41, 67, 119,
                94, 98, 74, 121, 194, 173, 103, 114, 107, 195, 65, 111, 179, 102, 112, 197, 191, 173, 123, 68, 152, 141,
                181, 34, 93, 99, 127, 188, 43, 139, 188, 152, 73, 142 };

        int[] uu = downscale(u, 10, 10);
        int[] vv = downscale(v, 10, 10);

        RgbToYuv420p transform = new RgbToYuv420p();
        Picture _in = Picture.createPicture(10, 10,
                new byte[][] { ArrayUtil.toByteArrayShifted(rgb), null, null, null }, ColorSpace.RGB);
        Picture out = Picture.create(10, 10, ColorSpace.YUV420);
        transform.transform(_in, out);

        Assert.assertArrayEquals(ArrayUtil.toByteArrayShifted(y), out.getPlaneData(0));
        Assert.assertArrayEquals(ArrayUtil.toByteArrayShifted(uu), out.getPlaneData(1));
        Assert.assertArrayEquals(ArrayUtil.toByteArrayShifted(vv), out.getPlaneData(2));
    }

    private int[] downscale(int[] plane, int w, int h) {
        int[] result = new int[w * h >> 2];
        for (int i = 0, k = 0; i < h; i += 2, k += w * 2) {
            for (int j = 0; j < w; j += 2) {
                int off1 = k + j;
                int off2 = (k >> 2) + (j >> 1);
                result[off2] = (plane[off1] + plane[off1 + 1] + plane[off1 + w] + plane[off1 + 1 + w] + 2) >> 2;
            }
        }

        return result;
    }
}
