package com.application.humming.form;

import lombok.Data;

@Data
public class SearchForm {

    /** 歌手名または曲名. */
    private String singerOrSong;
}
