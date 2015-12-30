package com.sinolife.learn.vido;

import com.github.hoary.javaav.Codec;

/**
 * @author yucl80@163.com
 *
 */

public class TestExample {
	public static void main(String[] args) throws Exception {
		for (String codec : Codec.getInstalledCodecs())
			System.out.println(codec);
	}
}
