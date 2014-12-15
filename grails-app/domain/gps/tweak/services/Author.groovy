package gps.tweak.services

class Author {

    String name
    String toString() { name }
    static hasMany = [books: Book]
    static mapping = {
        cache true
    }
}
