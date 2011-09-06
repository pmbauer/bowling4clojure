(ns bowling4clojure.test.core
  (:use [bowling4clojure.core])
  (:use [clojure.test]))

(deftest test-various-games
  (are [description expected-score game]
           (= expected-score (score game))
       "gutter game" 0 (repeat 0)
       "all ones" 20 (repeat 1)
       "one spare" 16 (concat [5 5 3] (repeat 0))
       "one strike" 24 (concat [10 3 4] (repeat 0))
       "perfect game" 300 (repeat 10)
    ))

(deftest test-frames-for-various-games
  (are [description expected-frames rolls]
           (= expected-frames (take 10 (frames rolls)))
       "gutter game" (repeat 10 [0 0]) (repeat 0) 
       "all ones" (repeat 10 [1 1]) (repeat 1) 
       "all spares" (repeat 10 [5 5 5]) (repeat 5)
       "all strikes" (repeat 10 [10 10 10]) (repeat 10)
    ))

(deftest test-frame-advance
  (are [description balls rolls]
           (= balls (frame-advance rolls))
       "no mark" 2 [6 3 5]
       "spare" 2 [6 4 5]
       "strike" 1 [10 0 5]
    ))

(deftest test-balls-in-frame
  (are [description balls rolls]
           (= balls (balls-in-frame rolls))
       "no mark" 2 [6 3 5]
       "spare" 3 [6 4 5]
       "strike" 3 [10 0 5]
    ))

(deftest test-spare?
  (are [rolls expected] (= expected (spare? rolls))
       [6 3] false
       [6 4] true
       [5 5] true))

(deftest test-strike?
  (are [rolls expected] (= expected (strike? rolls))
       [10 3] true 
       [6 10] false 
       [5 5] false))
