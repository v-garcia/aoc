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



(def answer2 (->> input
                  (map #(clojure.string/replace % (re-pattern (str/join "|" (keys d))) d))
                  (map #(Integer/parseInt
                         (str
                          (re-find #"\d" %)
                          (re-find #"\d" (apply str (reverse %))))))
                  #_(apply +)))

;; (spit "a2.edn" (with-out-str (clojure.pprint/pprint answer2)))

;; (clojure.string/replace "abcone2threexyz" (re-pattern (str/join "|" (keys d))) d)

;; ;54473

;; (clojure.string/replace "mnbrf3fourfpbrdgltf2xbmbmrbjltdxbklsixoneightq" (re-pattern (str/join "|" (keys d))) d)

;; (re-pattern (re-pattern (str/join "|" (into (keys d) (vals d)))))
;; "mnbrf3fourfpbrdgltf2xbmbmrbjltdxbklsixoneightq"

;; (def % "mnfourbrf3fourfpbrdgltf2xbmbmrbjltdxbklsixoneightq")
;; (identity
;;  (str
;;   (as-> (re-find (re-pattern (str/join "|" (into (keys d) (vals d)))) %) % (get d % %))
;;   (let [e 5] (as-> (re-find (re-pattern (str/join "|" (into (keys d) (vals d)))) %) % (get d % %)))))

;; (let [e 5] e)
;; (Integer/parseInt
;;  (str
;;   (re-find #"\d" %)
;;   (re-find #"\d" (apply str (reverse %)))))