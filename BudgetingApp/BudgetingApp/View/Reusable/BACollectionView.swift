//
//  BACollectionView.swift
//  BudgetingApp
//
//  Created by Aidyn Assan on 12.07.2022.
//

import UIKit

class BACollectionView: UIView, UICollectionViewDelegate, UICollectionViewDataSource {
	
    var myCatObjects: [(String, String, String, String)] = [] {
        didSet {
            collectionView.reloadData()
        }
    }
    var myCategories: [String] = [] {
        didSet {
            for i in 0..<myCategories.count {
                switch myCategories[i] {
                case Categories.beauty.rawValue: myCatObjects.append(MyFanctions.returnCategories(cat: .beauty))
                case Categories.cinema.rawValue: myCatObjects.append(MyFanctions.returnCategories(cat: .cinema))
                case Categories.clothes.rawValue: myCatObjects.append(MyFanctions.returnCategories(cat: .clothes))
                case Categories.fitness.rawValue: myCatObjects.append(MyFanctions.returnCategories(cat: .fitness))
                case Categories.furniture.rawValue: myCatObjects.append(MyFanctions.returnCategories(cat: .furniture))
                case Categories.games.rawValue: myCatObjects.append(MyFanctions.returnCategories(cat: .games))
                case Categories.medicine.rawValue: myCatObjects.append(MyFanctions.returnCategories(cat: .medicine))
                case Categories.restaurants.rawValue: myCatObjects.append(MyFanctions.returnCategories(cat: .restaurants))
                case Categories.taxi.rawValue: myCatObjects.append(MyFanctions.returnCategories(cat: .taxi))
                case Categories.travel.rawValue: myCatObjects.append(MyFanctions.returnCategories(cat: .travel))
                case Categories.noCat.rawValue: myCatObjects.append(MyFanctions.returnCategories(cat: .noCat))
                default: continue
                }
            }
        }
    }
    
	let collectionView: UICollectionView = {
		let layout = UICollectionViewFlowLayout()
		layout.sectionInset = UIEdgeInsets(top: 2, left: 12, bottom: 2, right: 5)
		layout.itemSize = CGSize(width: 90, height: 100)
		layout.scrollDirection = .horizontal
		let collectionView = UICollectionView(frame: .zero, collectionViewLayout: layout)
		collectionView.translatesAutoresizingMaskIntoConstraints = false
		return collectionView
	}()
	
	override init(frame: CGRect) {
		super.init(frame: frame)
		translatesAutoresizingMaskIntoConstraints = false
		backgroundColor = .systemGray6
		configure()
        getData()
	}
	
	required init?(coder: NSCoder) {
		fatalError("init(coder:) has not been implemented")
	}
	
    func getData() {
        NetworkManager.shared.getCategories(path: "current-combination"){ [weak self] result in
            switch result {
            case .success(let myCats): self?.myCategories = myCats
            case .failure: print("No Data")
            }
            self?.collectionView.reloadData()
        }
    }
	
	func configure() {
		addSubview(collectionView)
		collectionView.backgroundColor = .systemGray6
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
        myCatObjects.count
	}
	
	func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
		let cell = collectionView.dequeueReusableCell(withReuseIdentifier: BACollectionViewCell.cellID, for: indexPath) as! BACollectionViewCell
        cell.setup(myCatObjects[indexPath.row])
		return cell
	}
}
