(ns day4
  (:require
   [clojure.string :as str]))

(def data-test (slurp "2024/day04_sample.input"))

(def column-width (-> data-test (str/split #"\n") first count))
(def letters (-> data-test (str/replace #"\n" "")))

(def word-do-find "XMAS")

(defn adjacent-position
  [position]
  (let [row (quot position column-width)
        in-range? #(and (>= % 0) (< % (count letters)))
        left (when (and (in-range? (dec position)) (= row (quot (dec position) column-width))) (dec position))
        right (when (and (in-range? (inc position)) (= row (quot (inc position) column-width))) (inc position))
        top (when (in-range? (- position column-width)) (- position column-width))
        bottom (when (in-range? (+ position column-width)) (+ position column-width))
        left-top (when (and left top) (- top 1))
        right-top (when (and right top) (+ top 1))
        left-bottom (when (and left bottom) (- bottom 1))
        right-bottom (when (and right bottom) (+ bottom 1))]
    {:left left, :right right, :top top, :bottom bottom,
     :left-top left-top, :right-top right-top, :left-bottom left-bottom, :right-bottom right-bottom}))

(def all-directions [:left :right :top :bottom :left-top :right-top :left-bottom :right-bottom])

(comment 
(def 
  res
  (reduce
 (fn [acc letter]
   (mapcat
    (fn [[pos direction]]
      (when-let [next-pos (get (adjacent-position pos) direction)]
          (when (= (get letters next-pos) letter)
            [[next-pos direction]]
            )))
    acc))

 (for [pos (map second (filter #(= (first word-do-find) (first %))
                               (map vector letters (range))))
       direction all-directions]
   [pos direction])

 (rest word-do-find))
)

(count res)
)