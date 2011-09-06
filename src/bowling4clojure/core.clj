(ns bowling4clojure.core)

(defn spare? [[first second & _]]
  (= 10 (+ first second)))

(defn strike? [[first & _]]
  (= 10 first))

(defn balls-in-frame [frame]
  (cond (spare? frame) 3
        (strike? frame) 3
        :else 2))

(defn frame-advance [frame]
  (if (strike? frame) 1 2))

(defn frames [rolls]
  (when-let [rolls (seq rolls)]
    (lazy-seq
      (cons (take (balls-in-frame rolls) rolls)
            (frames (drop (frame-advance rolls) rolls))))))

(defn score-frame [frame]
  (reduce + frame))

(defn score [game]
  (->> (frames game)
       (take 10)
       (map score-frame)
       (reduce +)))
