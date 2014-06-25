package org.farng.mp3.util;
import java.io.File;
import java.io.IOException;

import org.farng.mp3.MP3File;
import org.farng.mp3.id3.AbstractID3v2;
import org.farng.mp3.id3.ID3v1;
import org.farng.mp3.lyrics3.AbstractLyrics3;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.audio.mp3.MP3AudioHeader;
import org.jaudiotagger.tag.TagException;

/**
 * MP3工具
 */
public class MP3Util {

	/**
	 * 获取MP3 的 ID3信息
	 * 
	 * @param path
	 * @return
	 */
	public static MP3Info getMP3Info(String path) {
		MP3File file = null;
		MP3Info info = null;
		try {
			file = new MP3File(path);
			AbstractLyrics3 lyrics = file.getLyrics3Tag();
			AbstractID3v2 id3v2 = file.getID3v2Tag();
			ID3v1 id3v1 = file.getID3v1Tag();
			if(id3v1 == null && id3v2 == null){
				return null;
			}
			info =  new MP3Info(id3v2, id3v1, lyrics);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		try {
			File mp3File = new File(path);
			org.jaudiotagger.audio.mp3.MP3File mp3fi = (org.jaudiotagger.audio.mp3.MP3File) AudioFileIO.read(mp3File);
			MP3AudioHeader audioHeader = (MP3AudioHeader)mp3fi.getAudioHeader();  
			info.setBitRate(audioHeader.getBitRate());
			info.setTrackLength(audioHeader.getTrackLengthAsString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return info;
	}

	public static void main(String[] args) throws Exception {
		String path;
		// String path = "D:/mp3/song.mp3";
//		 String path = "E:/樱桃时光/熬夜/思念的/回忆里的青春/你不是真正的快乐 - 五月天.mp3";
//		path = "E:/樱桃时光/熬夜/思念的/回忆里的青春";
//		eachFiles(path);
//		path = "E:/樱桃时光/熬夜/思念的/甜蜜";
//		//path = "E:/樱桃时光/熬夜/思念的/牵挂/当爱已成往事（霸王别姬 电影插曲） - 张国荣.mp3";
		path = "E:/樱桃时光/熬夜/思念的/回忆里的青春/江美琪 - 亲爱的你怎么不在我身边.mp3";
		eachFiles(path);
		// getMP3Info(path);
	}

	public static void eachFiles(String path) throws Exception {
		File file = new File(path);
		if (file.exists()) {
			if (file.isFile()) {
				test(file.getAbsolutePath());
			} else if (file.isDirectory()) {
				File[] files = file.listFiles();
				for (File file2 : files) {
					eachFiles(file2.getAbsolutePath());
				}
			} else {
				return;
			}
		}
	}

	public static void test(String path) throws Exception {
//		System.out.println("==================================================");
//		System.out.println(path);
//		System.out.println("==================================================");
		MP3Info info = getMP3Info(path);
		if(info != null){
			info.printMP3Info();
		}
		//
	}
}
