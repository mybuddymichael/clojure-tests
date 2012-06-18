(ns tests.michael)
(use 'clojure.test)
(alias 'string 'clojure.string)


(deftest truthiness-negation-and-nil
  (is (= 3 3))
  (is (not (= 3 4)))

  (is (= true (not false)))
  (is (= false (not true))))


(deftest arithmetic
  (is (= true (number? 3)))

  (is (= 7 (+ 3 4)))
  (is (= 3 (- 7 4)))
  (is (= -3 (- 4 7)))

  (is (= 12 (* 3 4)))
  (is (= 12.0 (* 3 4.0)))

  (is (= 7/3 (/ 7 3)))
  (is (= 2.5 (/ 5 2.0))))


(deftest strings
  (is (= true (string? "Hello.")))

  (is (= "Hello, Michael." (str "Hello, " "Michael.")))
  (is (= "HELLO!" (string/upper-case "Hello!"))))


(deftest collections
  (is (= true (vector? [3 4 7])))
  (is (= true (list? '(3 4 7))))
  (is (= true (map? {3 4, 7 8})))
  (is (= true (set? #{3 4 7})))

  (is (= [3 4 7 8 9] (conj [3 4 7] 8 9)))
  (is (= '(9 8 3 4 7) (conj '(3 4 7) 8 9))))


(deftest functions
  (defn square [x] (* x x))
  (def cube (fn [x] (* x x x)))

  (is (= true (fn? square)))

  (is (= 9 (square 3)))
  (is (= 27 (cube 3))))

  (is (= 9 ((fn [x] (* x x)) 3)))


(run-tests)
