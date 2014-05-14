package com.sunxd.encrypt;

public class Encrypt {

	static void main(String []args)
	{
		new XXTEA();
	}
}


//7D69117B15254717636A3B631C5F3A41096D5E5B525A5E1978590203130849285E06
/* XXTEA.java
*
* Author:       Ma Bingyao <andot@ujn.edu.cn>
* Copyright:    CoolCode.CN
* Version:      1.6
* LastModified: 2006-08-09
* This library is free.  You can redistribute it and/or modify it.
* http://www.coolcode.cn/?p=169
*/

//package org.phprpc.util;


class XXTEA {
   XXTEA() {}

   /** 
    * @param args 
    */ 
   public static void main(String[] args) { 
       String s = args[0];//"ÄãºÃ£ºandot¡£"; 
       //String s=ox(7D69117B15254717636A3B631C5F3A41096D5E5B525A5E1978590203130849285E06);
       byte sin[]={0x7D, 0x69, 0x11, 0x7B, 0x15, 0x25, 0x47, 0x17, 0x63,
               0x6A, 0x3B, 0x63, 0x1C, 0x5F, 0x3A, 0x41, 0x09, 0x6D,
               0x5E, 0x5B, 0x52, 0x5A, 0x5E, 0x19, 0x78, 0x59, 0x02,
               0x03, 0x13, 0x08, 0x49, 0x28, 0x5E, 0x06};

       byte[] k = "1234567890abcdef".getBytes(); 
       //System.out.println(""+2&3);
       byte [] d=decrypt(sin,k);
       System.out.println(new String(d));
       //byte[] e = encrypt(s.getBytes(), k); 
       //byte[] d = decrypt(e, k); 
       //System.out.print(new String(d)); 
   } 

   /**
    * Encrypt data with key.
    * 
    * @param data
    * @param key
    * @return
    */
   public static byte[] encrypt(byte[] data, byte[] key) {
       if (data.length == 0) {
           return data;
       }
       return toByteArray(
               encrypt(toIntArray(data, true), toIntArray(key, false)), false);
   }

   /**
    * Decrypt data with key.
    * 
    * @param data
    * @param key
    * @return
    */
   public static byte[] decrypt(byte[] data, byte[] key) {
       if (data.length == 0) {
           return data;
       }
       return toByteArray(
               decrypt(toIntArray(data, false), toIntArray(key, false)), true);
   }

   /**
    * Encrypt data with key.
    * 
    * @param v
    * @param k
    * @return
    */
   public static int[] encrypt(int[] v, int[] k) {
       int n = v.length - 1;

       if (n < 1) {
           return v;
       }
       if (k.length < 4) {
           int[] key = new int[4];

           System.arraycopy(k, 0, key, 0, k.length);
           k = key;
       }
       int z = v[n], y = v[0], delta = 0x9E3779B9, sum = 0, e;
       int p, q = 6 + 52 / (n + 1);

       while (q-- > 0) {
           sum = sum + delta;
           e = sum >>> 2 & 3;
           for (p = 0; p < n; p++) {
               y = v[p + 1];
               z = v[p] += (z >>> 5 ^ y << 2) + (y >>> 3 ^ z << 4)
                       ^ (sum ^ y) + (k[p & 3 ^ e] ^ z);
           }
           y = v[0];
           z = v[n] += (z >>> 5 ^ y << 2) + (y >>> 3 ^ z << 4)
                   ^ (sum ^ y) + (k[p & 3 ^ e] ^ z);
       }
       return v;
   }

   /**
    * Decrypt data with key.
    * 
    * @param v
    * @param k
    * @return
    */
   public static int[] decrypt(int[] v, int[] k) {
       int n = v.length - 1;

       if (n < 1) {
           return v;
       }
       if (k.length < 4) {
           int[] key = new int[4];

           System.arraycopy(k, 0, key, 0, k.length);
           k = key;
       }
       int z = v[n], y = v[0], delta = 0x9E3779B9, sum, e;
       int p, q = 6 + 52 / (n + 1);

       sum = q * delta;
       while (sum != 0) {
           e = sum >>> 2 & 3;
           for (p = n; p > 0; p--) {
               z = v[p - 1];
               y = v[p] -= (z >>> 5 ^ y << 2) + (y >>> 3 ^ z << 4)
                       ^ (sum ^ y) + (k[p & 3 ^ e] ^ z);
           }
           z = v[n];
           y = v[0] -= (z >>> 5 ^ y << 2) + (y >>> 3 ^ z << 4)
                   ^ (sum ^ y) + (k[p & 3 ^ e] ^ z);
           sum = sum - delta;
       }
       return v;
   }

   /**
    * Convert byte array to int array.
    * 
    * @param data
    * @param includeLength
    * @return
    */
   private static int[] toIntArray(byte[] data, boolean includeLength) {
       int n = (((data.length & 3) == 0)
               ? (data.length >>> 2)
               : ((data.length >>> 2) + 1));
       int[] result;

       if (includeLength) {
           result = new int[n + 1];
           result[n] = data.length;
       } else {
           result = new int[n];
       }
       n = data.length;
       for (int i = 0; i < n; i++) {
           result[i >>> 2] |= (0x000000ff & data[i]) << ((i & 3) << 3);
       }
       return result;
   }

   /**
    * Convert int array to byte array.
    * 
    * @param data
    * @param includeLength
    * @return
    */
   private static byte[] toByteArray(int[] data, boolean includeLength) {
       int n = data.length << 2;

       ;
       if (includeLength) {
           int m = data[data.length - 1];

           if (m > n) {
               return null;
           } else {
               n = m;
           }
       }
       byte[] result = new byte[n];

       for (int i = 0; i < n; i++) {
           result[i] = (byte) ((data[i >>> 2] >>> ((i & 3) << 3)) & 0xff);
       }
       return result;
   }
}