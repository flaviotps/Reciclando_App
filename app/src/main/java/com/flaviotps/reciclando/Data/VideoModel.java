package com.flaviotps.reciclando.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VideoModel {

    public String Cod;
    public String Url;
    public String Title;
    public String Description;
    public String Page;
    private PageModel TabPage;

    public static List<VideoModel> getVideosByPage(int PageID, List<VideoModel> videos) {
        List<VideoModel> newVideos = new ArrayList<>();

        for (int i = 0; i < videos.size(); i++) {
            if (videos.get(i).getTabPage().getIntPage() == PageID) {
                newVideos.add(videos.get(i));
            }

        }


        return newVideos;
    }

    public static List<PageModel> GetUniquePages(List<VideoModel> videos) {

        if (videos == null) {
            return null;
        }

        List<PageModel> tabPages = new ArrayList<>();

        for (int i = 0; i < videos.size(); i++) {
            boolean found = false;
            for (int j = 0; j < tabPages.size(); j++) {
                if (tabPages.get(j).Number == (videos.get(i).getTabPage().Number)) {
                    found = true;
                }
            }

            if (!found) {
                tabPages.add(videos.get(i).getTabPage());
            }
        }

        return tabPages;
    }

    public PageModel getTabPage() {
        return TabPage;
    }

    public void setTabPage(PageModel tabPage) {
        TabPage = tabPage;
    }


    public static String getYoutubeThumbnailUrlFromVideoUrl(String videoUrl) {
        String imgUrl = "https://img.youtube.com/vi/" + getYoutubeVideoIdFromUrl(videoUrl) + "/mqdefault.jpg";
        return imgUrl;
    }

    public static String getYoutubeVideoIdFromUrl(String inUrl) {
        if (inUrl.toLowerCase().contains("youtu.be")) {
            return inUrl.substring(inUrl.lastIndexOf("/") + 1);
        }
        String pattern = "(?<=watch\\?v=|/videos/|embed\\/)[^#\\&\\?]*";
        Pattern compiledPattern = Pattern.compile(pattern);
        Matcher matcher = compiledPattern.matcher(inUrl);
        if (matcher.find()) {
            return matcher.group();
        }
        return null;
    }


    public String toString() {
        return "<Title:" + Title + ">" + "<URL:" + Url + ">" + "<Page:" + Page + ">" + "<Description:" + Description + ">";
    }
}
