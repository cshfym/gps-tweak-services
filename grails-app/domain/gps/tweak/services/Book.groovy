package gps.tweak.services

class Book {
    String title
    String toString() { title }
    static belongsTo = [author: Author]
    static mapping = {
        cache true
    }
}
