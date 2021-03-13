package tads.eaj.ufrn.minhaprova.sqllite

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns
import android.util.Log

class LivroDBOpener  (context : Context) : SQLiteOpenHelper(context,
    LivroContrato.DATABASE_NAME, null,
    LivroContrato.DATA_BASE_VERSION
) {

    val TAG = "sql"
    val SQL_CREATE_TABLE =
        "CREATE TABLE ${LivroContrato.LivroEntry.TABLE_NAME}" +
                "( ${BaseColumns._ID} INTEGER PRIMARY KEY, " +
                " ${LivroContrato.LivroEntry.NOME} TEXT," +
                " ${LivroContrato.LivroEntry.AUTOR} TEXT," +
                " ${LivroContrato.LivroEntry.ANO} INTEGER," +
                " ${LivroContrato.LivroEntry.NOTA} REAL" +
                ")"
    val SQL_DROP_TABLE =
        "DROP TABLE ${LivroContrato.LivroEntry.TABLE_NAME}"

    override fun onCreate(db: SQLiteDatabase) {
        Log.i(TAG, "Banco de dados Criado")
        db.execSQL(SQL_CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    fun insert (l: Livro){
        var banco: SQLiteDatabase = writableDatabase
        try {

            var values = ContentValues()
            values.put(LivroContrato.LivroEntry.NOME, l.nome)
            values.put(LivroContrato.LivroEntry.AUTOR, l.autor)
            values.put(LivroContrato.LivroEntry.ANO, l.ano)
            values.put(LivroContrato.LivroEntry.NOTA, l.nota)

            banco.insert(LivroContrato.LivroEntry.TABLE_NAME, null, values)
            //banco.insert(CarroContrato.CarroEntry.TABLE_NAME, CarroContrato.CarroEntry.NOME, values)

        }finally {
            banco.close()
        }
    }

    fun update (l: Livro){
        var banco: SQLiteDatabase = writableDatabase
        try {

            var values = ContentValues()
            values.put(LivroContrato.LivroEntry.NOME, l.nome)
            values.put(LivroContrato.LivroEntry.AUTOR, l.autor)
            values.put(LivroContrato.LivroEntry.ANO, l.ano)
            values.put(LivroContrato.LivroEntry.NOTA, l.nota)

            var selection = "${LivroContrato.LivroEntry.NOME} = ?"
            var whereArgs = arrayOf("${l.nome}")

            banco.update(LivroContrato.LivroEntry.TABLE_NAME, values, selection, whereArgs)

        }finally {
            banco.close()
        }
    }

    fun findById(id:Int): Livro {
        var banco: SQLiteDatabase = readableDatabase
        try{

            var selection = "${BaseColumns._ID} = ?"
            var whereArgs = arrayOf("${id}")
            val cursor: Cursor = banco.query(LivroContrato.LivroEntry.TABLE_NAME, null, selection, whereArgs, null, null, null, null)

            cursor.moveToFirst()

            var livro = Livro()
            livro.id = cursor.getInt(cursor.getColumnIndex(BaseColumns._ID))
            livro.nome = cursor.getString(cursor.getColumnIndex(LivroContrato.LivroEntry.NOME))
            livro.autor = cursor.getString(cursor.getColumnIndex(LivroContrato.LivroEntry.AUTOR))
            livro.ano = cursor.getInt(cursor.getColumnIndex(LivroContrato.LivroEntry.ANO))
            livro.nota = cursor.getFloat(cursor.getColumnIndex(LivroContrato.LivroEntry.NOTA))

            return livro

        }finally {
            banco.close()
        }
    }

}