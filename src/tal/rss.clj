(ns tal.rss)
(load "scraper")
(require '[clj-rss.core :as rss])

(defn episode-mp3-url [episode]
  (str "http://audio.thisamericanlife.org/jomamashouse/ismymamashouse/" (:number episode) ".mp3")
)

(defn merge-mp3-url [episode]
  (merge episode {:url (episode-mp3-url episode)})
)

(defn episode-list []
  (map merge-mp3-url (tal.scraper/episode-list))
)

(defn rss-definition []
  (concat
    [{:title "TAL Bigger RSS Feed" :description "This American Life Podcast Uberfeed" :link "http://github.com/kevin1024/tal/"}]
    (for [episode (episode-list)]
      {
        :title (str (:name episode) " (#" (:number episode) ")")
        :link (:url episode)
        :description (:description episode)
        :enclosure (:url episode)
        :pubDate (:date episode)
      }
    )
  )
)

(defn main []
  (apply rss/channel-xml (rss-definition))
)

