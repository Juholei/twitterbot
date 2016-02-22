# twitterbot

Tweets a link from a text file with the title of the linked page also added to the tweet. Removes the link from the file. Links can also be sent to the associated Twitter account via direct messages, and when this is run they will be added to the text file to be tweeted.


I did this to learn some Clojure and it's my first thing written in it.

![Example of the resulting tweet](screenshot.png "Example of the resulting tweet")

See it in action at [@coding_daily](https://twitter.com/coding_daily)!

## Installation

Checkout, build with Leiningen with command "lein uberjar"

## Usage
1. Create file things_to_tweet.txt to the same folder where the jar is. Put links inside the file separated by a newline.
2. Fill Twitter app and user account tokens and the screen name of the user account you want to use to add links via direct message in credentials.edn.example and save it to the same folder with the jar with the name credentials.edn.
3. Run with the command: java -jar twitterbot-0.1.0-standalone.jar
4. If you want to add links, send them to the associated Twitter account from your account (that is set as the trusted account in credentials.edn). Direct messages are checked and links from them are added to the things_to_tweet.txt when run next time.

For additional fun use cron to schedule the tweets, for example "0 */4 * * * java -jar /path/to/jar/file/"

## License

Released under the MIT License: http://www.opensource.org/licenses/mit-license.php
