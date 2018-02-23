import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class HashedDictionary < K, V > implements DictionaryInterface < K, V >
{
    private TableEntry < K, V > [] hashTable; 
    private int numberOfEntries;
    private int locationsUsed; 
    private static final int DEFAULT_SIZE = 101; 
    private static final double MAX_LOAD_FACTOR = 0.5; 
    
 
	public HashedDictionary ()
    {
        this (DEFAULT_SIZE); 
    } 


    public HashedDictionary (int tableSize)
    {
       // int primeSize = getNextPrime (tableSize);
        hashTable = new TableEntry [23];
        numberOfEntries = 0;
        locationsUsed = 0;
    } 


	public V getValue (K key)
    {
        V result = null;
      /*  int index = getHashIndex (key);
        index = locate (index, key);
        if (index != -1)
            result = hashTable [index].getValue (0); 
       */
        return result;
    } 


    public V remove (K key)
    {
    	V toRemove = null;
    	for(int i = 0; i < numberOfEntries; i++)
    	{
    		if(hashTable[i].getKey() == key)
    		{
    			toRemove = hashTable[i].getValue(0);
    			numberOfEntries--;
    		}
    		hashTable[i] = hashTable[i+1];
    	}
    	return toRemove;
    } 


    private int locate (int index, K key)
    { 
    	int idx = -1;
    	if(hashTable[index].getKey() == key)
    	{
    		idx = index;
    	}
    	return idx;
    } 


    public V add (K key, V value)
    {   
    	hashTable[numberOfEntries] = new TableEntry<K, V>(key, value);
    	numberOfEntries++;
    	return value;
    } 

/*
    private int probe (int index, K key)
    {
    } 

    */

    private void rehash ()
    {
        TableEntry < K, V > [] oldTable = hashTable;
        int oldSize = hashTable.length;
        int newSize = oldSize + oldSize;
        hashTable = new TableEntry [newSize];
        numberOfEntries = 0; 
        
        locationsUsed = 0;
        
        for (int index = 0 ; index < oldSize ; index++)
        {
            if ((oldTable [index] != null) && oldTable [index].isIn ())
            	for(int idx = 0; idx < oldTable[index].value.size(); idx++)
                add (oldTable [index].getKey (), oldTable [index].getValue (idx));
        }
    } 


    private class KeyIterator implements Iterator < K >
    {
        private int currentIndex; 
        private int numberLeft; 
        private KeyIterator ()
        {
            currentIndex = 0;
            numberLeft = numberOfEntries;
        } 
        public boolean hasNext ()
        {
            return numberLeft > 0;
        } 
        public K next ()
        {
            K result = null;
            if (hasNext ())
            {
                while ((hashTable [currentIndex] == null) ||
                        hashTable [currentIndex].isRemoved ())
                {
                    currentIndex++;
                } 
                result = hashTable [currentIndex].getKey ();
                numberLeft--;
                currentIndex++;
            }
            else
                throw new NoSuchElementException ();
            return result;
        } 
        public void remove ()
        {
            throw new UnsupportedOperationException ();
        } 
    } 

    private class ValueIterator implements Iterator < V >
    {
        private int currentIndex; 
        private int numberLeft; 
        private ValueIterator ()
        {
            currentIndex = 0;
            numberLeft = numberOfEntries;
        } 
        public boolean hasNext ()
        {
            return numberLeft > 0;
        } 
        public V next ()
        {
            V result = null;
            if (hasNext ())
            {
                while ((hashTable [currentIndex] == null) ||
                        hashTable [currentIndex].isRemoved ())
                {
                    currentIndex++;
                } 
                result = hashTable [currentIndex].getValue (0);
                numberLeft--;
                currentIndex++;
            }
            else
                throw new NoSuchElementException ();
            return result;
        } 
        public void remove ()
        {
            throw new UnsupportedOperationException ();
        } 
    } 

    private class TableEntry < K, V >
    {
        private K key;
        private ArrayList<V> value;
        private TableEntry next;
        private int entries = -1;
        private boolean inTable; // true if entry is in hash table
        private TableEntry (K searchKey, V dataValue)
        {
            key = searchKey;
            value.add(dataValue);
            inTable = true;
        } 
      
        public boolean isIn() {
			// TODO Auto-generated method stub
			return false;
		}

		public boolean isRemoved() 
        {
			if(!inTable)
				return true;
			return false;
		}
		public K getKey()
        {
        	return key;
        }
        public V getValue(int vIndex)
        {
        	return value.get(vIndex);
        }
    }


	@Override
	public boolean contains(K key) 
	{
		for (int i = 0; i < numberOfEntries; i++)
		{
			if(hashTable[i].getKey() == key)
			{
				return true;
			}
		}
			return false;
	}


	@Override
	public Iterator<K> getKeyIterator() {
		// TODO Auto-generated method stub
		return new KeyIterator();
	}


	@Override
	public Iterator<V> getValueIterator() {
		// TODO Auto-generated method stub
		return new ValueIterator();
	}


	@Override
	public boolean isEmpty() 
	{
		if(numberOfEntries == 0)
			return true;
		return false;
	}


	@Override
	public int getSize() 
	{
		return numberOfEntries;
	}


	@Override
	public void clear() 
	{
		numberOfEntries = 0;	
	}
} 