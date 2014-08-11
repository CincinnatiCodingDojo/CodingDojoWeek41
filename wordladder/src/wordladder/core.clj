(ns wordladder.core
(:require [clojure.java.io :refer :all])
(:gen-class))

(def alphabet (set (map char (range 97 123))))

(defn read-word-set []
	(set (with-open [rdr (reader "../word.list")]
		(doall (line-seq rdr)
			))))

(def dictionary (read-word-set))

(defn find-word [word word-set]
	(contains? word-set word)
	)

(defn all-other-letters [letter]
	(disj alphabet letter)
	)
(defn vector-contains [previous-words]
	(fn [element]
		(some #(= element %) previous-words))
	)

(defn generate-next-words [word previous-words]
	(remove (vector-contains previous-words) (let [letters (apply vector word)]
		(for [i (range 0 (count letters))
			  replacement (all-other-letters (nth letters i))]
			(apply str (assoc letters i replacement)))
		)))

(defn filter-words-in-list [listGen listOrg]
   (filter listOrg listGen)
)

(defn inner-solve [current-word end-word path]
	(if (= current-word end-word)
		path
	  (let [generated-words (generate-next-words current-word path)
	  	    valid-words (filter-words-in-list generated-words dictionary)]
	; HELP ME
	(inner-solve new-word end-word (conj new-word path)))))

(defn solve [start-word end-word]
	(inner-solve start-word end-word [])
	  	    ; (recur (first valid-words) (conj path (first valid-words))))) 
)
