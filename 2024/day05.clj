(ns day05
  (:require
   [clojure.string :as str]))

;; Some parsing
(def rows (slurp "2024/day05.input"))
(def parts (str/split rows #"\n\n"))

(def rules (mapv #(let [[p1 p2] (str/split % #"\|")]
                    [(Integer/parseInt p1) (Integer/parseInt p2)]) (str/split (first parts) #"\n")))
(def updates (mapv (fn [l] (mapv #(Integer/parseInt %) (str/split l #","))) (str/split (second parts) #"\n")))

;; Better data structure
(def after (update-vals (group-by first rules) #(set (mapv second %))))
(def before (update-vals (group-by second rules) #(set (mapv first %))))

;; Some fns
(defn is-before? [a b]
  (let [afters (get after a #{})
        befores (get before b #{})]
    (or (contains? afters b)
        (contains? befores a))))

(defn get-middle
  [update]
  (nth update (quot (count update) 2)))

(defn validate-update
  [[before & after]]
  (or (empty? after)
      (and
       (every? #(is-before? before %) after)
       (validate-update after))))

;; Q1
(->> updates
     (filter validate-update)
     (mapv get-middle)
     (apply +))

;; Q2

(defn bubble-sort [xs]
  (let [ys (reduce (fn [ys x]
                     (if-let [y (peek ys)]
                       (if (is-before? x y)
                         (conj (pop ys) x y)
                         (conj ys x))
                       [x])) [] xs)]
    (if (= xs ys)
      xs
      (recur ys))))

(->> updates
     (remove validate-update)
     (mapv bubble-sort)
     (mapv get-middle)
     (apply +))