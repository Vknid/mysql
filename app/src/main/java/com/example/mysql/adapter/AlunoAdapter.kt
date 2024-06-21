package com.example.mysql.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mysql.databinding.ActivityItemAlunoBinding
import com.example.mysql.model.Aluno


class AlunoAdapter : RecyclerView.Adapter<AlunoAdapter.AlunoViewHolder>() {
    private var alunos: List<Aluno> = emptyList()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AlunoAdapter.AlunoViewHolder {
        val binding = ActivityItemAlunoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AlunoViewHolder (binding)
    }

    override fun onBindViewHolder(holder: AlunoAdapter.AlunoViewHolder, position: Int) {
        val aluno = alunos[position]
        holder.bind(aluno)
    }

    override fun getItemCount(): Int {
        return alunos.size
    }

    fun setData (alunos: List<Aluno>){
        this.alunos = alunos
        notifyDataSetChanged()
    }

    inner class AlunoViewHolder(private val binding: ActivityItemAlunoBinding) :
    RecyclerView.ViewHolder(binding.root){
        fun bind (aluno: Aluno){
            binding.apply {
                nomeTextView.text = aluno.nome
                cpfTextView.text = aluno.cpf
                emailTextView.text = aluno.email
                matriculaTextView.text = aluno.matricula
            }
        }
    }

}
