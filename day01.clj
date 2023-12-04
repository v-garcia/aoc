(ns day1
  (:require
   [clojure.string :as str]))

(def input (->
            (slurp "day1.input")
            (str/split #"\n")
            ))


;; Q1
(def answer1 (->> input
                  (map #(Integer/parseInt 
                         (str 
                          (re-find #"\d" %)
                          (re-find #"\d" (str/reverse %))
                          )))
                  (apply +)))

;; Q2

(def d {"one" "1"
          "two" "2"
          "three" "3"
          "four" "4"
          "five" "5"
          "six" "6"
          "seven" "7"
          "eight" "8"
          "nine" "9"})

(def d' (update-keys d str/reverse))

(def answer2 (->> input
                  (map #(Integer/parseInt
                         (str
                          (let [r (re-find (re-pattern (str/join "|" (into (keys d) (vals d)))) %)] (get d r r))
                          (let [r (re-find (re-pattern (str/join "|" (into (keys d') (vals d')))) (str/reverse %))] (get d' r r)))))
                  (apply +)))