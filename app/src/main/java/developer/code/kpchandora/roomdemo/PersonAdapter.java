package developer.code.kpchandora.roomdemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.MyHolder> {

    private final LayoutInflater inflater;
    private List<PersonEntity> personList;
    private PersonClickListener listener;

    PersonAdapter(Context context) {
        inflater = LayoutInflater.from(context);
        this.listener = (PersonClickListener)context;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.recyclerview_item, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {

        final PersonEntity currentPerson = personList.get(position);
        if (personList != null) {
            holder.nameTextView.setText(currentPerson.getFirstName() + " " + currentPerson.getLastName());
            holder.mobileTextView.setText(currentPerson.getMobileNumber());
        } else {
            holder.nameTextView.setText("No person");
            holder.mobileTextView.setVisibility(View.GONE);
        }

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                listener.onPersonClick(currentPerson);
                return false;
            }
        });


    }

    public interface PersonClickListener {
        void onPersonClick(PersonEntity personEntity);
    }

    void setWord(List<PersonEntity> people) {
        personList = people;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (personList != null)
            return personList.size();
        return 0;
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        private final TextView nameTextView;
        private final TextView mobileTextView;

        public MyHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.person_name_textView);
            mobileTextView = itemView.findViewById(R.id.person_mobile_num_textView);
        }
    }
}
