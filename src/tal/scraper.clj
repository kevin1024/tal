(ns tal.scraper
  (:require [net.cgrand.enlive-html :as enlive]))

(def base-url "http://www.thisamericanlife.org/radio-archives")

(def episode-list-selector [:#archive-episodes :div.episode-archive ])

(defn fetch-url [url]
    (enlive/html-resource (java.net.URL. url)))

(defn extract-name-field [episode]
  "extract the field that has both the name and number, used by extract-name and extract-episode-number"
  (first (:content (first (enlive/select episode [:h3 :a]))))
)

(defn extract-name [episode]
  (clojure.string/trim (second (clojure.string/split (extract-name-field episode) #":")))
)

(defn extract-episode-number [episode]
  (first (clojure.string/split (extract-name-field episode) #":"))
)

(defn extract-image [episode]
  (:src (:attrs (first (enlive/select episode [:a.image :img]))))
)

(defn extract-description [episode]
  (apply clojure.string/trim (:content (first (enlive/select episode [:div.content]))))
)

(defn extract-episode [episode]
  {
   :name (extract-name episode)
   :number (extract-episode-number episode)
   :image (extract-image episode)
   :description (extract-description episode)
  }
)

(defn episode-list []
    (map extract-episode (enlive/select (fetch-url base-url) episode-list-selector))
)
