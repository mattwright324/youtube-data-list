# youtube-data-list
Makes use of the Youtube Data list functions. Retrieve information about videos, channels, comments, playlists. Search normally or by location. 

Also allows you to measure total quota used during it's instantiation.

Google provides a [guide](https://developers.google.com/youtube/v3/getting-started) to obtaining a youtube data key. 
The [Data API documentation](https://developers.google.com/youtube/v3/docs/) is a good resource to see what information can be gathered with each list function.
However, this library does not implement parameters and options that require authorization.

Some code examples:

    YouTubeData3 data = new YouTubeData3("youtubeDataApiKeyHere");
    
    // Comments posted on a video:
    try {
        CommentThreadsList ctl;
        String pageToken = "";
        int pages = 0;
        do {
            ctl = ((CommentThreadsList) data.commentThreadsList().part(Parts.SNIPPET))
                    .getThreadsByVideo("dQw4w9WgXcQ", pageToken);
            pageToken = ctl.getNextPageToken();

            if(ctl.hasItems()) {
                for(CommentThreadsList.Item item : ctl.getItems()) {
                    if(item.hasSnippet()) {
                        CommentsList.Item.Snippet topLevelComment = item.getSnippet().getTopLevelComment().getSnippet();

                        String author = topLevelComment.getAuthorDisplayName();
                        String comment = topLevelComment.getTextDisplay();

                        System.out.printf("%s: %s\r\n", author, comment);
                    }
                }
            } else {
                System.out.println("CommentThreadsList example didn't return any results.");
            }
            pages++;
        } while (ctl.getNextPageToken() != null && pages < 3);
    } catch (YouTubeErrorException e) {
        System.out.println(e.getError().getMessage());
    }

    // Find videos near you:
    try {
        SearchList sl;
        String pageToken = "";
        int pages = 0;
        do {
            // Location takes latitude,longitude and a radius using m, km, ft, or mi.
            sl = ((SearchList) data.searchList().part(Parts.SNIPPET))
                    .order(SearchList.ORDER_DATE)
                    .getByLocation("", pageToken, "40.2822047,-76.9154449", "1mi");
            pageToken = sl.getNextPageToken();

            if(sl.hasItems()) {
                for(SearchList.Item item : sl.getItems()) {
                    if(item.hasSnippet()) {
                        SearchList.Item.Snippet snippet = item.getSnippet();

                        String date = snippet.getPublishedAt().toString();
                        String channelTitle = snippet.getChannelTitle();
                        String videoTitle = snippet.getTitle();
                        String videoId = item.getId().getVideoId();

                        System.out.printf("%s  https://youtu.be/%s (%s): %s\r\n",
                                date, videoId, channelTitle, videoTitle);
                    }
                }
            } else {
                System.out.println("SearchList example didn't return any results.");
            }
            pages++;
        } while (sl.getNextPageToken() != null && pages < 3);
    } catch (YouTubeErrorException e) {
        System.out.println(e.getError().getMessage());
    }

    // See how much quota you used!
    System.out.printf("Total quota units spent this session: %,d\n\n", data.getTotalSpentCost());
