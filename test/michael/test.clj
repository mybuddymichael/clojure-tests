(ns michael.test
  (:require [clojure.test :refer [deftest is testing]]
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


(deftest regular-expressions
  (def search-string "Michael, The Bear")
  (is (= "The Bear" (re-find #"The Bear" search-string)))
  (is (= nil (re-find #"The Monkey" search-string))))


(deftest collections
  (is (vector? [3 4 7]))
  (is (list? '(3 4 7)))
  (is (map? {3 4, 7 8}))
  (is (set? #{3 4 7}))

  (testing "Empty collections do not evaluate to nil"
    (is (not (nil? '())))
    (is (not (nil? [])))
    (is (not (nil? {})))
    (is (not (nil? #{}))))

  (is (= [3 4 7 8 9] (conj [3 4 7] 8 9)))
  (is (= '(9 8 3 4 7) (conj '(3 4 7) 8 9)))

  (is (= 3 (first [3 4 7 8])))
  (is (= [4 7 8] (rest [3 4 7 8])))
  (is (= 8 (last [3 4 7 8])))

  (is (= '() (rest [])))
  (is (= nil (next [])))

  (testing "retrieving values from a map"
    (def my-map {:first-name "Michael"})
    (is (= "Michael" (my-map :first-name)))
    (is (= "Michael" (:first-name my-map)))))


(deftest functions
  (testing "defn is a shortcut for def <symbol> (fn ..."
    (defn square [x] (* x x))
    (def cube (fn [x] (* x x x))))

  (testing "documenting a function"
    (defn add-one
      "Returns a number equal to x plus 1"
      [x]
      (+ x 1))
    (is (= 4 (add-one 3)))

    (defn add-two
      {:doc "Returns a number equal to x plus 2"}
      [x]
      (+ x 2))
    (is (= 5 (add-two 3))))

  (is (fn? square))

  (is (= 9 (square 3)))
  (is (= 27 (cube 3)))

  (is (= 9 ((fn [x] (* x x)) 3))))


(deftest recursion
  (defn factorial [x]
    (if (= x 1)
        1
        (* x (factorial (- x 1)))))
  (is (= 24 (factorial 4))))


(deftest destructuring
  (def my-name ["Michael" "the" "Bear"])
  (def my-name-map {:f-name "Michael" :m-name "the" :l-name "Bear"})

  (testing "destructuring a vector"
    (let [[f-name m-name l-name] my-name]
      (is (= "the Bear, Michael" (str m-name " " l-name ", " f-name)))))

  (testing "destructuring a map"
    (let [{f :f-name m :m-name l :l-name} my-name-map]
      (is (= "Michael" f))
      (is (= "the" m)))
    (let [{:keys [m-name]} my-name-map]
      (is (= "the" m-name))))

  (testing "gathering up extra values"
    (let [[x & rest] my-name]
      (is (= "Michael" x))
      (is (= '("the" "Bear") rest))))

  (is (= 10 (apply + [1 2 3 4]))))


(deftest functional-standbys
  (is (= 10 (reduce + [1 2 3 4])))
  (is (= [2 4 6 8] (map (fn [x] (* x 2)) [1 2 3 4])))
  (is (= [2 4 6 8] (map #(* % 2) [1 2 3 4]))))


(deftest clojure-core-utilities
  (is (= "a-keyword" (name :a-keyword))))
