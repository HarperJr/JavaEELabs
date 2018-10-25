package com.vlsu.com.vlsu.test

import com.vlsu.com.vlsu.JDBCTools
import com.vlsu.com.vlsu.dao.EnchantDAO
import com.vlsu.com.vlsu.dao.ToolDAO
import com.vlsu.com.vlsu.models.Enchant
import com.vlsu.com.vlsu.models.Tool
import org.junit.*
import java.sql.Connection
import java.sql.Time
import java.util.concurrent.TimeUnit

class JUnit {
    private val HOST = "localhost"
    private val PORT = 3306

    private var connection: Connection? = null

    @Before
    fun getConnection() {
        val jdbcTools = JDBCTools()
        jdbcTools.connect(HOST, PORT, "tools_database")
        connection = jdbcTools.connection
    }

    @Test
    fun insertionTest() {
        val toolDAO = ToolDAO(connection)

        //Test
        val tool = Tool("Pickaxe").setHardness(1.4f).setDamage(0.5f)

        toolDAO.create(tool)

        assert(toolDAO.all.last().equals(tool))
    }

    @Test
    fun selectionTest() {
        val enchantDAO = EnchantDAO(connection)

        val enchant = enchantDAO.getById(1)
        val expectingResult = Enchant("Frost decaying")
                .setDescription("An ancient evil from frost and ice pleasing for murdering")
                .setDuration(Time(TimeUnit.MILLISECONDS.toHours(10L)))
        assert(enchant.equals(expectingResult))
    }

    @Test
    fun removingTest() {
        val toolDAO = ToolDAO(connection)

        assert(toolDAO.delete(11) && toolDAO.getById(11) == null)
    }

    @Test
    fun updatingTest() {
        val enchantDAO = EnchantDAO(connection)

        val enchant =  Enchant("Decaying")
                .setDescription("Decaying bodies mixing their rot with flesh")
                .setDuration(Time(TimeUnit.MILLISECONDS.toHours(10L)))

        val result = enchantDAO.update(1, enchant)
        assert(enchant.equals(enchantDAO.getById(1)))

    }
}