package com.se.pranita.termproject.utils;

import com.se.pranita.termproject.model.Course;

import java.util.Calendar;

/**
 * Created by Pranita on 22/4/16.
 */
public class TermUtil {
    public static Term getCurrentTerm() {
        if(Calendar.getInstance().get(Calendar.MONTH) < 5 && Calendar.getInstance().get(Calendar.MONTH) >= 0){
            return Term.SPRING;
        } else if (Calendar.getInstance().get(Calendar.MONTH) < 12 && Calendar.getInstance().get(Calendar.MONTH) >= 7) {
            return Term.FALL;
        } else {
            return Term.SUMMER;
        }
    }

    public enum Term {
        SPRING(0), SUMMER(1), FALL(2);

        int value;

        Term(int value) {
            this.value = value;
        }

        public static Term getTerm(String term) {
            if (term.equalsIgnoreCase("spring"))
                return SPRING;
            else if (term.equalsIgnoreCase("fall"))
                return FALL;
            else if (term.equalsIgnoreCase("summer"))
                return SUMMER;
            else
                return null;
        }

        public int getValue() {
            return this.value;
        }
    }

    public static int getCurrentYear() {
        return Calendar.getInstance().get(Calendar.YEAR);
    }

    /**
     * Compare two terms and return
     * -1 if term1 is less than term2
     * 0 if terms are equal
     * 1 if term1 is greater than term2
     * @return
     */
    public static int compareTerm(Term term1Sem, int term1Year, Term term2Sem, int term2Year) {
        if(term1Year == term2Year) {
            if(term1Sem.getValue() == term2Sem.getValue())
                return 0;
            else if(term1Sem.getValue() < term2Sem.getValue())
                return -1;
            else
                return 1;
        } else if(term1Year < term2Year) {
            return -1;
        } else {
            return 1;
        }
    }

    public static int compareCurrentTerm(Term term1Sem, int term1Year) {
        return compareTerm(getCurrentTerm(), getCurrentYear(), term1Sem, term1Year);
    }
}
