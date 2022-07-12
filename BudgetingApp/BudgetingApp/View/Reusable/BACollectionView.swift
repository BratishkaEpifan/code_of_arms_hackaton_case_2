//
//  BACollectionView.swift
//  BudgetingApp
//
//  Created by Aidyn Assan on 12.07.2022.
//

import UIKit

class BACollectionView: UIView, UICollectionViewDelegate, UICollectionViewDataSource {
	
	let collectionView: UICollectionView = {
		let layout = UICollectionViewFlowLayout()
		layout.sectionInset = UIEdgeInsets(top: 12, left: 12, bottom: 5, right: 5)
		layout.itemSize = CGSize(width: 90, height: 100)
		layout.scrollDirection = .horizontal
		let collectionView = UICollectionView(frame: .zero, collectionViewLayout: layout)
		collectionView.translatesAutoresizingMaskIntoConstraints = false
		return collectionView
	}()
	
	override init(frame: CGRect) {
		super.init(frame: frame)
		translatesAutoresizingMaskIntoConstraints = false
		configure()
	}
	
	required init?(coder: NSCoder) {
		fatalError("init(coder:) has not been implemented")
	}
	
	
	func configure() {
		addSubview(collectionView)
		collectionView.showsHorizontalScrollIndicator = false
		collectionView.dataSource = self
		collectionView.delegate = self
		collectionView.register(BACollectionViewCell.self,
								forCellWithReuseIdentifier: BACollectionViewCell.cellID)
		NSLayoutConstraint.activate([
			collectionView.topAnchor.constraint(equalTo: topAnchor),
			collectionView.leadingAnchor.constraint(equalTo: leadingAnchor),
			collectionView.trailingAnchor.constraint(equalTo: trailingAnchor),
			collectionView.bottomAnchor.constraint(equalTo: bottomAnchor)
		])
	}

	func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
		Category.categories.count
	}
	
	func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
		let cell = collectionView.dequeueReusableCell(withReuseIdentifier: BACollectionViewCell.cellID, for: indexPath) as! BACollectionViewCell
		cell.setup(Category.categories[indexPath.row])
		return cell
	}
}
