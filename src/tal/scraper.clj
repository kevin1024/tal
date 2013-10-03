(ns tal.scraper
  (:require [net.cgrand.enlive-html :as enlive])
  (:require [org.httpkit.client :as http]))

(def base-url "http://www.thisamericanlife.org/radio-archives")

(def episode-list-selector [:#archive-episodes :div.episode-archive ])

(defn fetch-url [url]
  (enlive/html-resource (java.net.URL. url)))

(defn url-exists [url]
  (let [{:keys [status headers body error] :as resp} @(http/head url)]
  (= status 200)
))

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

(defn parse-date [date]
  (.parse (java.text.SimpleDateFormat. "MM.dd.yyyy") date ))

(defn extract-date [episode]
  (parse-date (first (:content (first (enlive/select episode [:.date]))))))

(defn episode-mp3-url [episode-number]
  (str "http://audio.thisamericanlife.org/jomamashouse/ismymamashouse/" episode-number ".mp3")
)

(defn extract-episode [episode]
  {
   :name (extract-name episode)
   :number (extract-episode-number episode)
   :image (extract-image episode)
   :description (extract-description episode)
   :date (extract-date episode)
   :url (episode-mp3-url (extract-episode-number episode))
  }
)

(defn filter-out-unpublished-episodes [episodes]
  "check the first episode's URL to make sure it exists"
  (if (url-exists (:url (first episodes)))
    episodes
    (rest episodes)
  )
)

(defn all-episodes []
  "returns a list of all scraped episodes, which might include unpublished episodes"
  (map extract-episode (enlive/select (fetch-url base-url) episode-list-selector))
)


(defn episode-list []
    (filter-out-unpublished-episodes (all-episodes))
)
