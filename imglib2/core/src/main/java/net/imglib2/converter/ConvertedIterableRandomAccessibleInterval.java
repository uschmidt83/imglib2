package net.imglib2.converter;

import java.util.Iterator;

import net.imglib2.Interval;
import net.imglib2.IterableInterval;
import net.imglib2.IterableRealInterval;
import net.imglib2.Positionable;
import net.imglib2.RandomAccess;
import net.imglib2.RandomAccessible;
import net.imglib2.RandomAccessibleInterval;
import net.imglib2.RealPositionable;
import net.imglib2.converter.sampler.SamplerConverter;

public class ConvertedIterableRandomAccessibleInterval< A, B, S extends RandomAccessible< A > & IterableInterval< A > > implements IterableInterval< B >, RandomAccessibleInterval< B >
{
	private final S source;

	private final SamplerConverter< A, B > converter;

	public ConvertedIterableRandomAccessibleInterval( final S source, final SamplerConverter< A, B > converter )
	{
		this.source = source;
		this.converter = converter;
	}

	@Override
	public int numDimensions()
	{
		return source.numDimensions();
	}

	@Override
	public RandomAccess< B > randomAccess()
	{
		return new ConvertedRandomAccess< A, B >( converter, source.randomAccess() );
	}

	@Override
	public RandomAccess< B > randomAccess( final Interval interval )
	{
		return new ConvertedRandomAccess< A, B >( converter, source.randomAccess( interval ) );
	}

	@Override
	public long min( final int d )
	{
		return source.min( d );
	}

	@Override
	public void min( final long[] min )
	{
		source.min( min );
	}

	@Override
	public void min( final Positionable min )
	{
		source.min( min );
	}

	@Override
	public long max( final int d )
	{
		return source.max( d );
	}

	@Override
	public void max( final long[] max )
	{
		source.max( max );
	}

	@Override
	public void max( final Positionable max )
	{
		source.max( max );
	}

	@Override
	public void dimensions( final long[] dimensions )
	{
		source.dimensions( dimensions );
	}

	@Override
	public long dimension( final int d )
	{
		return source.dimension( d );
	}

	@Override
	public double realMin( final int d )
	{
		return source.realMin( d );
	}

	@Override
	public void realMin( final double[] min )
	{
		source.realMin( min );
	}

	@Override
	public void realMin( final RealPositionable min )
	{
		source.realMin( min );
	}

	@Override
	public double realMax( final int d )
	{
		return source.realMax( d );
	}

	@Override
	public void realMax( final double[] max )
	{
		source.realMax( max );
	}

	@Override
	public void realMax( final RealPositionable max )
	{
		source.realMax( max );
	}

	@Override
	public long size()
	{
		return source.size();
	}

	@Override
	public Object iterationOrder()
	{
		return source.iterationOrder();
	}

	@Override
	public boolean equalIterationOrder( final IterableRealInterval< ? > f )
	{
		return iterationOrder().equals( f.iterationOrder() );
	}

	@Override
	public Iterator< B > iterator()
	{
		return cursor();
	}

	@Override
	public B firstElement()
	{
		return cursor().next();
	}

	@Override
	public ConvertedCursor< A, B > cursor()
	{
		return new ConvertedCursor< A, B >( converter, source.cursor() );
	}

	@Override
	public ConvertedCursor< A, B > localizingCursor()
	{
		return new ConvertedCursor< A, B >( converter, source.localizingCursor() );
	}
}
