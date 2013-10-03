(ns tal.rss)
(load "scraper")
(require '[clj-rss.core :as rss])


(defn episode-list []
  (tal.scraper/episode-list)
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

