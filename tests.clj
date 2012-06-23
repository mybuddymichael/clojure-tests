(ns michael.test
  (:require [clojure.test :refer [deftest is run-tests]]
            [clojure.string :as string]))


(deftest truthiness-negation-and-nil
  (is true)
  (is (not false))
  (is (= false (not true)))

  (is (= 3 3))
  (is (not (= 3 4)))
  (is (not= 3 4))

  (is (true? true))
  (is (false? false))
  (is (not (true? 3)))
  (is (true? (= 3 3)))

  (is nil? nil)
  (is not (nil? false)))


(deftest arithmetic
  (is (number? 3))

  (is (= 7 (+ 3 4)))
  (is (= 3 (- 7 4)))
  (is (= -3 (- 4 7)))

  (is (= 12 (* 3 4)))
  (is (= 12.0 (* 3 4.0)))

  (is (= 7/3 (/ 7 3)))
  (is (= 2.5 (/ 5 2.0))))


(deftest strings
  (is (string? "Hello."))

  (is (= "Hello, Michael." (str "Hello, " "Michael.")))
  (is (= "HELLO!" (string/upper-case "Hello!"))))


(deftest collections
  (is (vector? [3 4 7]))
  (is (list? '(3 4 7)))
  (is (map? {3 4, 7 8}))
  (is (set? #{3 4 7}))

  (is (not (nil? ())))
  (is (not (nil? [])))
  (is (not (nil? {})))
  (is (not (nil? #{})))

  (is (= [3 4 7 8 9] (conj [3 4 7] 8 9)))
  (is (= '(9 8 3 4 7) (conj '(3 4 7) 8 9))))


(deftest functions
  (defn square [x] (* x x))
  (def cube (fn [x] (* x x x)))

  (is (fn? square))

  (is (= 9 (square 3)))
  (is (= 27 (cube 3))))

  (is (= 9 ((fn [x] (* x x)) 3)))


(run-tests)
