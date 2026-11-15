package com.example.projectpulih

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projectpulih.databinding.FragmentJournalListBinding

/**
 * Tab 3: Menampilkan Jurnal Bulan Ini dengan RecyclerView (Pertemuan 10)
 */
class JournalMonthlyFragment : Fragment() {

    private var _binding: FragmentJournalListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentJournalListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val journalAdapter = JournalAdapter(JournalDataRepository.monthlyJournals) { selectedItem ->
            Toast.makeText(
                requireContext(),
                "Jurnal Bulan Ini: ${selectedItem.moodName} (${selectedItem.date})",
                Toast.LENGTH_SHORT
            ).show()
        }

        binding.rvJournalList.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = journalAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
