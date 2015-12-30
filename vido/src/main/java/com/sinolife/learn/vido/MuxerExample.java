package com.sinolife.learn.vido;

import com.github.hoary.javaav.AudioFrame;
import com.github.hoary.javaav.Codec;
import com.github.hoary.javaav.CodecID;
import com.github.hoary.javaav.Demuxer;
import com.github.hoary.javaav.MediaFrame;
import com.github.hoary.javaav.Muxer;
import com.github.hoary.javaav.PixelFormat;
import com.github.hoary.javaav.VideoFrame;

/**
 * @author yucl80@163.com
 *
 */

public class MuxerExample {

	public static void main(String[] args) throws Exception {
		Muxer muxer = new Muxer("d:/tmp/test.mp4");
		muxer.setVideoCodec(Codec.getEncoderById(CodecID.H264));
		muxer.setAudioCodec(Codec.getEncoderById(CodecID.MP3));
		muxer.setImageWidth(600);
		muxer.setImageHeight(350);
		muxer.setGOPSize(25);
		muxer.setPixelFormat(PixelFormat.YUV420P);
		muxer.setVideoBitrate(2000000);
		muxer.setAudioBitrate(128000);
		muxer.setFramerate(25);
		muxer.setSamplerate(24000);
		muxer.setAudioChannels(1);
		muxer.open();


		Demuxer demuxer = new Demuxer();
		demuxer.open("d:/tmp/bunny.mp4");

		MediaFrame mediaFrame;
		while ((mediaFrame = demuxer.readFrame()) != null) {
			if (mediaFrame.getType() == MediaFrame.Type.VIDEO) {
				VideoFrame frame = (VideoFrame) mediaFrame;
				muxer.addImage(frame);
			}
			if (mediaFrame.getType() == MediaFrame.Type.AUDIO) {
				AudioFrame frame = (AudioFrame) mediaFrame;
				muxer.addSamples(frame);
			}
		}

		demuxer.close();
		muxer.close();
	}

}