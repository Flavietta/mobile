package org.bouncycastle.crypto.modes.gcm;

import org.bouncycastle.crypto.util.Pack;

public class Tables64kGCMMultiplier
    implements GCMMultiplier
{
    private final int[][][] M = new int[16][][];
    public  Tables64kGCMMultiplier() {
        // workaround for Windows Phone
        for(int iter = 0 ; iter < M.length ; iter++) {
            M[iter] = new int[256][];
        }
    }

    public void init(byte[] H)
    {            
        M[0][0] = new int[4];
        M[0][128] = GCMUtil.asInts(H);
        for (int j = 64; j >= 1; j >>= 1)
        {
            int[] tmp = new int[4];
            System.arraycopy(M[0][j + j], 0, tmp, 0, 4);

            GCMUtil.multiplyP(tmp);
            M[0][j] = tmp;
        }

        int i = 0;
        for (;;)
        {
            for (int j = 2; j < 256; j += j)
            {
                for (int k = 1; k < j; ++k)
                {
                    int[] tmp = new int[4];
                    System.arraycopy(M[i][j], 0, tmp, 0, 4);

                    GCMUtil.xor(tmp, M[i][k]);
                    M[i][j + k] = tmp;
                }
            }

            if (++i == 16)
            {
                return;
            }

            M[i][0] = new int[4];
            for (int j = 128; j > 0; j >>= 1)
            {
                int[] tmp = new int[4];
                System.arraycopy(M[i - 1][j], 0, tmp, 0, 4);

                GCMUtil.multiplyP8(tmp);
                M[i][j] = tmp;
            }
        }
    }

    public void multiplyH(byte[] x)
    {
//      assert x.Length == 16;

        int[] z = new int[4];
        for (int i = 15; i >= 0; --i)
        {
//            GCMUtil.xor(z, M[i][x[i] & 0xff]);
            int[] m = M[i][x[i] & 0xff];
            z[0] ^= m[0];
            z[1] ^= m[1];
            z[2] ^= m[2];
            z[3] ^= m[3];
        }

        Pack.intToBigEndian(z, x, 0);
    }
}
