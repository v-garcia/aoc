(ns day2
  (:require
   [clojure.string :as str]))

(def input (->>
            (str/split (slurp "day02.input") #"\n")
            (map #(str/replace % #"Game (\d)+:" ""))
            (mapv (fn [l] (map (fn [s] (-> 
                                        (apply hash-map (str/split (str/trim s) #",? "))
                                        clojure.set/map-invert
                                        (update-vals #(Integer/parseInt %))
                                        )) (str/split l #";"))))))

;; Q1
(def bag {"red" 12 "green" 13 "blue" 14})

(->> input
     (mapcat (fn [i g] 
               (map #(into [(inc i)] %) g))  (range))
     (remove (some  ))
     )

