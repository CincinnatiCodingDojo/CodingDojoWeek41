(ns wordladder.core-test
  (:require [midje.sweet :refer :all]
            [wordladder.core :refer :all]))

(def word-set (read-word-set))

(fact "can read from a static word list file" 
	(count word-set) => 264097)

(fact "can find determine if a word is valid"
	(find-word "test" #{ "test" "other" "words" }) => true
	(find-word "best" #{ "test" "other" "words" }) => false)

(fact "gimme all the letters in the alphabet besides the given letter"
	(all-other-letters \a) =not=> (contains \a)
	(count (all-other-letters \a)) => 25
	)

(fact "test valid next word"
	(let [words (generate-next-words "bart")]
		words => (contains "cart")
		words => (contains "bert")
		words => (contains "barn")
		words =not=> (contains "bart")
		words =not=> (contains "eark")))
(fact "can return only valid words from the word list"
	(let [next-words (generate-next-words "bart")]
		(filter-words-in-list next-words #{"bart" "bait"}) => (contains "bait")
		))