# youtube-data-list
Makes use of the Youtube Data list functions. Retrieve information about videos, channels, comments, playlists. Search normally or by location. 

Google provides a [guide](https://developers.google.com/youtube/v3/getting-started) to obtaining a youtube data key. 
The [Data API documentation](https://developers.google.com/youtube/v3/docs/) is a good resource to see what information can be gathered with each list function.
However, this library does not implement parameters and options that require authorization.

A couple code examples:

    YouTubeData3 data = new YouTubeData3("youtubeDataApiKeyHere");
    
    # Comments posted on a channel:
    CommentThreadsList ctl = null;
    String pageToken = "";
    do {
        ctl = data.commentThreads().getByChannel(CommentThreadsList.PART_SNIPPET, "UCUcyEsEjhPEDf69RRVhRh4A", pageToken);
        pageToken = ctl.nextPageToken;
        for(CommentThreadsList.Item item : ctl.items) {
            if(item.hasSnippet()) {
                String author = item.snippet.topLevelComment.authorDisplayName;
                String comment = item.snippet.topLevelComment.textDisplay;
                System.out.println(author+": "+comment);
            }
        }
    } while (ctl.nextPageToken != null);
    
    # Find videos near you!
    SearchList sl = null;
    String pageToken = "";
    do {
        # Location takes latitude,longitude and a radius using m, km, ft, or mi.
        sl = data.searchList()
            .order(SearchList.ORDER_DATE)
            .getByLocation(SearchList.PART_SNIPPET, "", pageToken, "40.2822047,-76.9154449", "1mi");
        pageToken = sl.nextPageToken;
        for(SearchList.Item item : sl.items) {
            if(item.hasSnippet()) {
                String date = item.snippet.publishedAt.toString();
                String channelTitle = item.snippet.channelTitle;
                String videoTitle = item.snippet.title;
                String videoId = item.id.videoId;
                System.out.println(date+"  https://youtu.be/"+videoId+" ("+channelTitle+"): "+videoTitle);
            }
        }
    } while (sl.nextPageToken != null);
