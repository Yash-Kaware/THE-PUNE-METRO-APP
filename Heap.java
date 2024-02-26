import java.util.ArrayList;
import java.util.HashMap;

public class MetroHeap
{
    ArrayList<MetroStation> data = new ArrayList<>();
    HashMap<MetroStation, Integer> map = new HashMap<>();

    public void add(MetroStation station)
    {
        data.add(station);
        map.put(station, data.size() - 1);
        upheapify(data.size() - 1);
    }

    private void upheapify(int ci)
    {
        int pi = (ci - 1) / 2;
        if (isLarger(data.get(ci), data.get(pi)) > 0)
        {
            swap(pi, ci);
            upheapify(pi);
        }
    }

    private void swap(int i, int j)
    {
        MetroStation ith = data.get(i);
        MetroStation jth = data.get(j);

        data.set(i, jth);
        data.set(j, ith);
        map.put(ith, j);
        map.put(jth, i);
    }

    public void display()
    {
        System.out.println(data);
    }

    public int size()
    {
        return this.data.size();
    }

    public boolean isEmpty()
    {
        return this.size() == 0;
    }

    public MetroStation remove()
    {
        swap(0, this.data.size() - 1);
        MetroStation removedStation = this.data.remove(this.data.size() - 1);
        downheapify(0);

        map.remove(removedStation);
        return removedStation;
    }

    private void downheapify(int pi)
    {
        int lci = 2 * pi + 1;
        int rci = 2 * pi + 2;
        int mini = pi;

        if (lci < this.data.size() && isLarger(data.get(lci), data.get(mini)) > 0)
        {
            mini = lci;
        }

        if (rci < this.data.size() && isLarger(data.get(rci), data.get(mini)) > 0)
        {
            mini = rci;
        }

        if (mini != pi)
        {
            swap(mini, pi);
            downheapify(mini);
        }
    }

    public MetroStation get()
    {
        return this.data.get(0);
    }

    public int isLarger(MetroStation station1, MetroStation station2)
    {
        return station1.compareTo(station2);
    }

    public void updatePriority(MetroStation station)
    {
        int index = map.get(station);
        upheapify(index);
    }
}
