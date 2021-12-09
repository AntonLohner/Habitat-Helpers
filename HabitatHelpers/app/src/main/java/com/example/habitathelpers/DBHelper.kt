package com.example.habitathelpers

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context): SQLiteOpenHelper(context, DB_NAME, null, DB_VER) {

    override fun onCreate(db: SQLiteDatabase?) {
        db!!.execSQL(CREATE_TABLE_HABS)
        db.execSQL(CREATE_TABLE_PETS)
        initializeTables()
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL(DROP_TABLE_HABS)
        db.execSQL(DROP_TABLE_PETS)
        closeDB()
    }

    private fun initializeTables(){
        val db = this.readableDatabase
        val queryp = "SELECT * FROM pets"
        val p = db.rawQuery(queryp, null)
        val queryh = "SELECT * FROM habs"
        val h = db.rawQuery(queryh, null)
        if( p.count <= 0) {
            //initialize pets table
        }
        if( h.count <= 0) {
            //initialize habs table
        }
    }

    fun closeDB(){
        val db = this.readableDatabase
        if(db != null && db.isOpen) {
            db.close()
        }
    }

    fun getPetSize(): Int {
        val db = this.readableDatabase
        val query = "SELECT * FROM pets"
        val c = db.rawQuery(query, null)
        return c.count
    }

    fun getHabSize(): Int {
        val db = this.readableDatabase
        val query = "SELECT * FROM habs"
        val c = db.rawQuery(query, null)
        return c.count
    }

    fun createPet(){

    }

    fun createHab(){

    }

    fun getPet(petName: String): Pet{
        val query = "SELECT * FROM pets"
        val db = this.readableDatabase
        val c = db.rawQuery(query, null)
        var petAge = 1
        val newPet = Pet( "name", "species", "gender", petAge
            /*db_id = c.getInt(c.getColumnIndex(COL_ID)),
            checked = false,
            backdrop_path = c.getString(c.getColumnIndex(COL_BACKDROP)),
            genre_ids = emptyList(),
            id = c.getInt(c.getColumnIndex(COL_MOVIE_ID)),
            original_language = c.getString(c.getColumnIndex(COL_ORG_LANG)),
            original_title =  c.getString(c.getColumnIndex(COL_ORG_TITLE)),
            overview = c.getString(c.getColumnIndex(COL_OVERVIEW)),
            popularity = c.getDouble(c.getColumnIndex(COL_POPULARITY)),
            poster_path = c.getString(c.getColumnIndex(COL_POSTER)),
            release_date = c.getString(c.getColumnIndex(COL_RELEASE)),
            title = c.getString(c.getColumnIndex(COL_TITLE)),
            video = true,
            vote_average = c.getDouble(c.getColumnIndex(COL_VOTE_AVG)),
            vote_count= c.getInt(c.getColumnIndex(COL_VOTE_CNT)),*/
        )
        return newPet
    }

    fun getHab(){

    }

    fun updatePet(){

    }

    fun updateHab(){

    }

    fun deletePet(name: String){
        val db = this.writableDatabase
        db.delete("pets", "$COL_NAME = ?", arrayOf(name))
        db.close()
    }

    fun deleteHab(){

    }


/*    fun getAllMovies(db: DatabaseHelper): MutableList<MovieData> {
        var dataset : MutableList<MovieData> = mutableListOf<MovieData>()
        for (i in 0 until db.getSize()) {
            dataset.add(db.getMovie(i))
        }
        return dataset
    }

    fun insertAllMovies(){
        val myStaticMovie = this.movieList
        for(movie in myStaticMovie){
            val id = addMovie(movie)
        }
    }

    fun addMovie(movie: MovieData): Long{
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COL_MOVIE_ID, movie.id)
        values.put(COL_VOTE_AVG, movie.vote_average)
        values.put(COL_TITLE, movie.title)
        values.put(COL_ORG_TITLE, movie.original_title)
        values.put(COL_ORG_LANG, movie.original_language)
        values.put(COL_OVERVIEW, movie.overview)
        values.put(COL_POPULARITY, movie.popularity)
        values.put(COL_POSTER, movie.poster_path)
        values.put(COL_BACKDROP, movie.backdrop_path)
        values.put(COL_VOTE_CNT, movie.vote_count)
        values.put(COL_RELEASE, movie.release_date)
        return db.insert("movies", null, values)
    }

    @SuppressLint("Range")
    fun getMovie(pos: Int): MovieData {
        val query = "SELECT * FROM movies"
        val db = this.readableDatabase
        val c = db.rawQuery(query, null)
        c.moveToPosition(pos)
        val movie = MovieData(
            db_id = c.getInt(c.getColumnIndex(COL_ID)),
            checked = false,
            backdrop_path = c.getString(c.getColumnIndex(COL_BACKDROP)),
            genre_ids = emptyList(),
            id = c.getInt(c.getColumnIndex(COL_MOVIE_ID)),
            original_language = c.getString(c.getColumnIndex(COL_ORG_LANG)),
            original_title =  c.getString(c.getColumnIndex(COL_ORG_TITLE)),
            overview = c.getString(c.getColumnIndex(COL_OVERVIEW)),
            popularity = c.getDouble(c.getColumnIndex(COL_POPULARITY)),
            poster_path = c.getString(c.getColumnIndex(COL_POSTER)),
            release_date = c.getString(c.getColumnIndex(COL_RELEASE)),
            title = c.getString(c.getColumnIndex(COL_TITLE)),
            video = true,
            vote_average = c.getDouble(c.getColumnIndex(COL_VOTE_AVG)),
            vote_count= c.getInt(c.getColumnIndex(COL_VOTE_CNT)),
        )
        return movie
    }

    */

/*    fun updateMovie(movie: MovieData): Int{
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COL_MOVIE_ID, movie.id)
        values.put(COL_VOTE_AVG, movie.vote_average)
        values.put(COL_TITLE, movie.title)
        values.put(COL_ORG_TITLE, movie.original_title)
        values.put(COL_ORG_LANG, movie.original_language)
        values.put(COL_OVERVIEW, movie.overview)
        values.put(COL_POPULARITY, movie.popularity)
        values.put(COL_POSTER, movie.poster_path)
        values.put(COL_BACKDROP, movie.backdrop_path)
        values.put(COL_VOTE_CNT, movie.vote_count)
        values.put(COL_RELEASE, movie.release_date)
        return db.update("movies", values, "COL_ID = ?", arrayOf(movie.db_id.toString()))
    }*/

    companion object {
        private val DB_NAME = "hhelper.db"
        private val DB_VER = 1
        private val COL_ID = "id"
        // values for pets table
        private val COL_NAME = "name"
        private val COL_SPECIES = "species"
        private val COL_GENDER = "species"
        private val COL_AGE = "age"

        //values for habitat table
        private val COL_MATERIAL = "material"
        private val COL_WIDTH = "width"
        private val COL_LENGTH= "length"
        private val COL_HEIGHT = "height"

        // create table pets
        private val CREATE_TABLE_PETS = "CREATE TABLE IF NOT EXISTS pets " +
                "( $COL_ID INTEGER PRIMARY KEY, $COL_NAME TEXT, $COL_SPECIES TEXT, " +
                "$COL_GENDER TEXT, $COL_AGE INTEGER )"
        // create table habs
        private val CREATE_TABLE_HABS = "CREATE TABLE IF NOT EXISTS habs " +
                "( $COL_ID INTEGER PRIMARY KEY, $COL_MATERIAL TEXT, $COL_WIDTH INTEGER, " +
                "$COL_LENGTH INTEGER, $COL_HEIGHT INTEGER )"


        //delete table pets
        private val DROP_TABLE_PETS = "DROP TABLE IF EXISTS pets"
        //delete table habs
        private val DROP_TABLE_HABS = "DROP TABLE IF EXISTS habs"
    }
}
