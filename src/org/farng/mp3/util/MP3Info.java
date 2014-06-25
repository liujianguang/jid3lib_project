package org.farng.mp3.util;

import org.farng.mp3.id3.AbstractID3v2;
import org.farng.mp3.id3.ID3v1;
import org.farng.mp3.lyrics3.AbstractLyrics3;

/**
 * MP3信息
 * 
 * @author gary
 *
 */
public class MP3Info {

	/**
	 * 歌手
	 */
	private String artist;
	/**
	 * 歌名
	 */
	private String songTitle;
	/**
	 * 专辑名称
	 */
	private String albumTitle;
	/**
	 * 发行年份
	 */
	private String yearReleased;
	/**
	 * 歌词
	 */
	private String songLyric;
	

	/**
	 * 时长,单位秒(s)
	 */
	private String trackLength;
	
	/**
	 * 码率,单位是kbps(kbit/s)
	 */
	private String bitRate;

	/**
	 * MP3信息
	 * @param id3v2
	 * @param id3v1
	 * @param lyrics
	 */
	public MP3Info(AbstractID3v2 id3v2, ID3v1 id3v1, AbstractLyrics3 lyrics) {
		if (id3v1 != null) {
			artist = id3v1.getLeadArtist();
			songTitle = id3v1.getSongTitle();
			albumTitle = id3v1.getAlbumTitle();
			yearReleased = id3v1.getYearReleased();
		}
		if (id3v2 != null) {
			artist = StringUtil.isNull(artist) ? id3v2.getLeadArtist() : artist;
			songTitle = StringUtil.isNull(songTitle) ? id3v2.getSongTitle() : songTitle;
			albumTitle = StringUtil.isNull(albumTitle) ? id3v2.getAlbumTitle() : albumTitle;
			yearReleased = StringUtil.isNull(yearReleased) ? id3v2.getYearReleased() : yearReleased;
		}
		if (lyrics != null) {
			songLyric = lyrics.getSongLyric();
		}
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getSongTitle() {
		return songTitle;
	}

	public void setSongTitle(String songTitle) {
		this.songTitle = songTitle;
	}

	public String getAlbumTitle() {
		return albumTitle;
	}

	public void setAlbumTitle(String albumTitle) {
		this.albumTitle = albumTitle;
	}

	public String getYearReleased() {
		return yearReleased;
	}

	public void setYearReleased(String yearReleased) {
		this.yearReleased = yearReleased;
	}

	public String getSongLyric() {
		return songLyric;
	}

	public void setSongLyric(String songLyric) {
		this.songLyric = songLyric;
	}

	public void printMP3Info() {
		System.out.println("歌手:" + artist);
		System.out.println("歌曲名:" + songTitle);
		System.out.println("专辑名:" + albumTitle);
		System.out.println("发行年份:" + yearReleased);
		System.out.println("歌词:" + songLyric);
		System.out.println("时长(s):" + trackLength);
		System.out.println("码率(kbps):" + bitRate);
	}

	public String getTrackLength() {
		return trackLength;
	}

	public void setTrackLength(String trackLength) {
		this.trackLength = trackLength;
	}

	public String getBitRate() {
		return bitRate;
	}

	public void setBitRate(String bitRate) {
		this.bitRate = bitRate;
	}

}