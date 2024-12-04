package com.example.studentmanweek12

import android.os.Bundle
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class MainFragment : Fragment() {
    private lateinit var studentList: MutableList<Student>
    private lateinit var adapter: ArrayAdapter<String>
    private lateinit var listView: ListView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.activity_main_fragment, container, false)

        // Khởi tạo danh sách sinh viên
        studentList = mutableListOf(
            Student("Nguyễn Văn An", "SV001"),
            Student("Trần Thị Bảo", "SV002"),
            Student("Lê Hoàng Cường", "SV003"),
            Student("Phạm Thị Dung", "SV004"),
            Student("Đỗ Minh Đức", "SV005"),
            Student("Vũ Thị Hoa", "SV006"),
            Student("Hoàng Văn Hải", "SV007"),
        )
        listView = view.findViewById(R.id.listView)
        adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_list_item_1,
            studentList.map { "${it.name} (${it.id})" }
        )
        listView.adapter = adapter

        // Thiết lập Context Menu
        registerForContextMenu(listView)

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.main_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.add_new) {
            // Mở StudentFragment để thêm sinh viên
            findNavController().navigate(R.id.action_main_to_student)
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateContextMenu(
        menu: ContextMenu,
        v: View,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        requireActivity().menuInflater.inflate(R.menu.context_menu, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
        val selectedStudent = studentList[info.position]

        when (item.itemId) {
            R.id.edit -> {
                // Gửi thông tin sinh viên qua Bundle
                val bundle = Bundle()
                bundle.putSerializable("student", selectedStudent)
                findNavController().navigate(R.id.action_main_to_student, bundle)
            }
            R.id.remove -> {
                // Xóa sinh viên khỏi danh sách
                studentList.removeAt(info.position)
                adapter.notifyDataSetChanged()
            }
        }
        return super.onContextItemSelected(item)
    }
}
